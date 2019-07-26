package spring.controller;


import java.awt.image.BufferedImage;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.dahuangya.common.lib.annotation.NoNeedRight;
import com.dahuangya.common.lib.base.BusinessException;
import com.dahuangya.common.lib.base.Token;
import com.dahuangya.common.lib.file.dto.FileDto;
import com.dahuangya.common.lib.file.dto.FileTransferDto;
import com.dahuangya.common.lib.sdk.weixin.DhyWeixinClient;
import com.dahuangya.common.lib.sdk.weixin.dto.CreateMiniCodeDto;
import com.dahuangya.common.service.common.oid.itf.IOidService;
import com.dahuangya.common.service.file.itf.IFileService;
import com.dahuangya.common.shardingDB.TraceUtil;
import com.dahuangya.gateway.rbac.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 通用服务
 * @author lvaolin
 * @create 2019/6/12 11:13 AM
 */
@RestController
@Component
@Slf4j
@RequestMapping("/common/facade")
public class CommonController {

    @Autowired
    private Producer captchaProducer ;

    @Reference
    private IOidService oidService;

    @Reference
    private IFileService fileService;

    @RequestMapping(value = {"/file/upload/{fileType}"},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public Object fileUpload(HttpServletRequest request,HttpServletResponse response,@PathVariable("fileType") String fileType) throws BusinessException{

        if(fileType==null||(!fileType.equals("img")&&!fileType.equals("video"))){
            throw new BusinessException("","/file/upload/{fileType}中fileType只可以为img、video");
        }

        CommonsMultipartFile[] commonsMultipartFiles = FileUploadUtil.getFiles(request);
        if(commonsMultipartFiles == null||commonsMultipartFiles.length==0){
            throw new BusinessException("","没有检测到上传的文件");
        }

        List<FileDto> fileDtoList = new ArrayList<FileDto>();
        for (CommonsMultipartFile commonsMultipartFile:commonsMultipartFiles) {

            if(!commonsMultipartFile.getFileItem().getName().contains(".")){
                  throw new BusinessException("","请上传带后缀名称的图片或者小视频");
            }

            String originalName =commonsMultipartFile.getFileItem().getName();
            String[] tempArr = originalName.split("\\.");
            //后缀
            String suffix = tempArr[tempArr.length-1];

            Token token = TraceUtil.getTokenObject();
            //组装成需要的dto
            FileTransferDto file = new FileTransferDto();
            file.setUploadCompressed(false);

            file.setFileClassification(fileType==null?"img":fileType);
            file.setAppId(token.getAppId());
            file.setIsOpen(true);
            file.setName(oidService.generateObjectID()+"."+suffix);
            file.setContent(commonsMultipartFile.getFileItem().get());

//            try {
//                //上传
//                FileDto fileDto = fileService.upload(file);
//                fileDtoList.add(fileDto);
//            }catch (Exception e){
//                e.printStackTrace();
//                throw new BusinessException("",e.getMessage());
//            }


            FileDto fileDto = FileUploadUtil.upload(file);
            fileDtoList.add(fileDto);


        }

        //返回上传的文件path等信息
        return fileDtoList;
    }



    /**
     * 获取验证码服务  1、有效期到期自动生效 2、只能验证一次 3、验证码存入redis  4、登录时从redis获取验证
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/captcha/image")
    public ModelAndView getCaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        //集群模式从 redis获取
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //log.info("旧验证码: " + code );

        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = captchaProducer.createText();
        //log.info("新验证码："+capText);
        // store the text in the session   集群模式下存入 redis
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }


    @RequestMapping("/minicode/get-unlimited")
    @NoNeedRight
    Object getUnlimited(@RequestBody CreateMiniCodeDto createMiniCodeDto) throws BusinessException {

        byte[] b = DhyWeixinClient.getUnlimited(createMiniCodeDto);//获取图片
        //下面上传到oss
        Token token = TraceUtil.getTokenObject();
        //组装成需要的dto
        FileTransferDto file = new FileTransferDto();
        file.setUploadCompressed(false);

        file.setFileClassification("minicode");
        file.setAppId(token.getAppId());
        file.setIsOpen(true);
        file.setName(oidService.generateObjectID()+".jpeg");
        file.setContent(b);//字节数组

        //上传到oss，返回url
        FileDto fileDto = FileUploadUtil.upload(file);
        return fileDto;
    }

}
