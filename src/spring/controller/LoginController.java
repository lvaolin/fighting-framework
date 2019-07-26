package spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.dahuangya.common.lib.annotation.NoNeedLogin;
import com.dahuangya.common.lib.base.BusinessException;
import com.dahuangya.common.lib.base.Token;
import com.dahuangya.common.lib.sdk.weixin.DhyWeixinClient;
import com.dahuangya.common.lib.utils.StringUtil;
import com.dahuangya.gateway.common.constant.ErrorCode;
import com.dahuangya.gateway.rbac.vo.WeixinLoginRetVo;
import com.dahuangya.service.rbac.dto.DahuangyaRbacLoginDto;
import com.dahuangya.service.rbac.dto.DahuangyaRbacLoginOperateLogDto;
import com.dahuangya.service.rbac.dto.DahuangyaRbacMemberDto;
import com.dahuangya.service.rbac.dto.DahuangyaRbacUserDto;
import com.dahuangya.service.rbac.itf.IDahuangyaMiniProgramService;
import com.dahuangya.service.rbac.itf.IDahuangyaRbacLoginService;
import com.dahuangya.service.rbac.itf.IDahuangyaRbacMemberService;
import com.dahuangya.service.rbac.itf.IDahuangyaRbacUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 移动端路由
 * @author lvaolin
 * @create 2019/6/12 11:13 AM
 */
@RestController
@Component
@Slf4j
@RequestMapping("/rbac/facade")
public class LoginController {

    //会员默认角色编码
    private static final String memberDefaultRoleCode = "memberDefaultRoleCode";

    @Reference
    private IDahuangyaRbacLoginService dahuangyaRbacLoginService;
    @Reference
    private IDahuangyaRbacMemberService dahuangyaRbacMemberService;
    @Reference
    private IDahuangyaRbacUserService dahuangyaRbacUserService;
    @Reference
    private IDahuangyaMiniProgramService dahuangyaMiniProgramService;

    //-----------登录-------------

    /**
     * 登录接口
     *
     * @param dahuangyaRbacLoginDto DahuangyaRbacLoginDto
     *                              <p/>{
     *                              <p/>	"loginType":x,   //登录类型（小程序登录3,会员2，操作员1，主管理员0，saas平台管理员-1）
     *                              <p/>	"loginName":"dahuangya",  //登录账号，小程序登录不用传
     *                              <p/>	"loginPwd":"dahuangya"    //登录密码，小程序登录不用传
     *                              <p/>	"loginCode":"xxxxxxxxxxxxxxxxxx"    //只有小程序登录需要传
     *                              <p/>}
     * @return JSONObject
     * <p/>{
     * <p/>     "result": true,
     * <p/>     "value": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJbMSwxLDEsXCIxMDAsMjAwLDMwMFwiLFwiXCIsMCwwXSIsImV4cCI6MTU2MDEzNTA1NywiaWF0IjoxNTU5OTYyMjU3fQ.HRcEbgiwsSzOPp39BzYTypNgxN8IMqhjmMtZxYRKO9-BknbwSHb3--CMksec1fiKlfGl8vvrXo7rHNiQi7u-eg"
     * <p/> }
     * @throws BusinessException 业务异常
     *                           <p/>{
     *                           <p/>    "result": false,
     *                           <p/>    "error": {
     *                           <p/>        "code": "错误代码",
     *                           <p/>        "message": "错误信息"
     *                           <p/>    }
     *                           <p/>}
     */
    @RequestMapping("/login")
    @NoNeedLogin
    Object login(@RequestBody DahuangyaRbacLoginDto dahuangyaRbacLoginDto) throws Exception {
        if (dahuangyaRbacLoginDto.getLoginType() == null) {
            throw new BusinessException(ErrorCode.LOGIN_TYPE_NULL_ERROR_CODE, ErrorCode.LOGIN_TYPE_NULL_ERROR_MSG);
        }
        //登录类型（小程序登录3,会员2，操作员1，主管理员0，saas平台管理员-1）
        switch (dahuangyaRbacLoginDto.getLoginType()) {
            case -1:
                return null;
            case 0:
                return null;
            case 1:
                //管理登录
                DahuangyaRbacUserDto dahuangyaRbacUserDto = new DahuangyaRbacUserDto();
                dahuangyaRbacUserDto.setUserName(dahuangyaRbacLoginDto.getLoginName());
                dahuangyaRbacUserDto.setUserPassword(dahuangyaRbacLoginDto.getLoginPwd());
                //appId 固定为1，后期由前端传入
                dahuangyaRbacUserDto.setAppId(1);
                DahuangyaRbacUserDto dahuangyaRbacUserDtoResult = dahuangyaRbacUserService.selectByUserNameAndPwd(dahuangyaRbacUserDto);
                if (dahuangyaRbacUserDtoResult == null) {
                    throw new BusinessException(ErrorCode.LOGIN_FAILED_ERROR_CODE, ErrorCode.LOGIN_FAILED_ERROR_MSG);
                } else {
                    DahuangyaRbacLoginOperateLogDto dahuangyaRbacLoginOperateLogDto = new DahuangyaRbacLoginOperateLogDto();
                    dahuangyaRbacLoginOperateLogDto.setLoginId(dahuangyaRbacUserDtoResult.getId());
                    dahuangyaRbacLoginService.insertLoginHistory(dahuangyaRbacLoginOperateLogDto);
                    if (dahuangyaRbacUserDtoResult.getRoleCodeList() == null || dahuangyaRbacUserDtoResult.getRoleCodeList().isEmpty()) {
                        throw new BusinessException(ErrorCode.LOGIN_USER_NO_ROLE_ERROR_CODE, ErrorCode.LOGIN_USER_NO_ROLE_ERROR_MSG);
                    }
                    //登录成功，计算token 返回
                    Token token = new Token(dahuangyaRbacUserDtoResult.getAppId(), 1, dahuangyaRbacUserDtoResult.getId(), StringUtils.join(dahuangyaRbacUserDtoResult.getRoleCodeList().toArray(), ","), "", "", 0L);
                    return dahuangyaRbacLoginService.getTicket(token);
                }
            case 2:
                //会员登录
                DahuangyaRbacMemberDto dahuangyaRbacMemberDto = new DahuangyaRbacMemberDto();
                dahuangyaRbacMemberDto.setMemberLoginName(dahuangyaRbacLoginDto.getLoginName());
                dahuangyaRbacMemberDto.setMemberLoginPwd(dahuangyaRbacLoginDto.getLoginPwd());
                dahuangyaRbacMemberDto.setAppId(1);//appId 固定为1，后期由前端传入
                DahuangyaRbacMemberDto dahuangyaRbacMemberDtoResult = dahuangyaRbacMemberService.selectByMemberNameAndPwd(dahuangyaRbacMemberDto);
                if (dahuangyaRbacMemberDtoResult == null) {
                    throw new BusinessException(ErrorCode.LOGIN_FAILED_ERROR_CODE, ErrorCode.LOGIN_FAILED_ERROR_MSG);
                } else {
                    DahuangyaRbacLoginOperateLogDto dahuangyaRbacLoginOperateLogDto =
                            new DahuangyaRbacLoginOperateLogDto(
                                    dahuangyaRbacMemberDtoResult.getAppId(), dahuangyaRbacMemberDtoResult.getId(), "登录", "");

                    dahuangyaRbacLoginService.insertLoginHistory(dahuangyaRbacLoginOperateLogDto);
                    //登录成功，计算token 返回
                    Token token = new Token(dahuangyaRbacMemberDtoResult.getAppId(), 2, dahuangyaRbacMemberDtoResult.getId(), dahuangyaRbacMemberDtoResult.getRoleCode(), "", "", 0L);
                    return dahuangyaRbacLoginService.getTicket(token);
                }

            case 3:
                //小程序登录
                //小程序单点登录
                //返回：{token,newUser，memberDto }
                if(StringUtil.isEmpty(dahuangyaRbacLoginDto.getMiniProgramLoginCode())){
                    throw new BusinessException("","小程序登录失败，code不能为空");
                }

                WeixinLoginRetVo weixinLoginRetVo = new WeixinLoginRetVo();

                JSONObject jsonObject = DhyWeixinClient.jscode2session(dahuangyaRbacLoginDto.getMiniProgramLoginCode());
                String openId = jsonObject.getString("openid");
                DahuangyaRbacMemberDto rbacMemberDto = dahuangyaMiniProgramService.ssoLogin(jsonObject);

                if(rbacMemberDto==null||rbacMemberDto.getId()==null){
                    throw new BusinessException("","登录失败");
                }
                weixinLoginRetVo.setDahuangyaRbacMemberDto(rbacMemberDto);
                //生成合法的token返回前端
                //登录成功，计算token 返回
                Token token = new Token(rbacMemberDto.getAppId(), 2, rbacMemberDto.getId(), memberDefaultRoleCode, "", openId, 0L);
                weixinLoginRetVo.setToken(dahuangyaRbacLoginService.getTicket(token));
                return weixinLoginRetVo;

            default:
                throw new BusinessException(ErrorCode.LOGIN_TYPE_NOT_EXIST_ERROR_CODE, ErrorCode.LOGIN_TYPE_NOT_EXIST_ERROR_MSG);
        }

    }





}
