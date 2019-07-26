package spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.dahuangya.common.discovery.annotation.ApiContext;
import com.dahuangya.common.lib.annotation.NoNeedLogin;
import com.dahuangya.common.lib.annotation.NoNeedRight;
import com.dahuangya.common.lib.base.BusinessException;
import com.dahuangya.common.lib.base.PageObject;
import com.dahuangya.common.lib.base.Token;
import com.dahuangya.common.shardingDB.TraceUtil;
import com.dahuangya.gateway.rbac.vo.WeixinUserInfoVo;
import com.dahuangya.service.rbac.dto.*;
import com.dahuangya.service.rbac.itf.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Component
@Slf4j
@ResponseBody
@RequestMapping("/rbac/facade")
public class DahuangyaRbacController {
    @Reference
    private IDahuangyaRbacLoginService dahuangyaRbacLoginService;
    @Reference
    private IDahuangyaRbacUserService dahuangyaRbacUserService;
    @Reference
    private IDahuangyaRbacRoleService dahuangyaRbacRoleService;
    @Reference
    private IDahuangyaRbacMemberService dahuangyaRbacMemberService;
    @Reference
    private IDahuangyaRbacResourceService dahuangyaRbacResourceService;
    @Reference
    private IDahuangyaRbacModuleService dahuangyaRbacModuleService;
    @Reference
    private IDahuangyaRbacDepartmentService dahuangyaRbacDepartmentService;
    @Reference
    private IDahuangyaRbacAppService dahuangyaRbacAppService;




    //----------------管理员--------

    /**
     * 操作员列表接口
     *
     * @return JSONObject 数组
     *<p/> {
     *<p/>     "result": true,
     *<p/>     "value": [
     *<p/>         {
     *<p/>             "roleCode": null,
     *<p/>             "id": 1,
     *<p/>             "appId": 1,
     *<p/>             "userName": "dahuangya",
     *<p/>             "userPassword": "dahuangya",
     *<p/>             "mobile": null,
     *<p/>             "status": null,
     *<p/>             "type": null,
     *<p/>             "remark": "主管理员账号",
     *<p/>             "version": null
     *<p/>         },
     *<p/>         {
     *<p/>             "roleCode": null,
     *<p/>             "id": 6766597570048000,
     *<p/>             "appId": null,
     *<p/>             "userName": "zhuying",
     *<p/>             "userPassword": "zhuying",
     *<p/>             "mobile": null,
     *<p/>             "status": null,
     *<p/>             "type": null,
     *<p/>             "remark": "助赢科技管理账号",
     *<p/>             "version": null
     *<p/>         }
     *<p/>     ]
     *<p/> }
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/user/select-all")
    Object userSelectAll() throws BusinessException {
        return dahuangyaRbacUserService.selectAll();
    }

    /**
     * 操作员查询接口
     *
     * @param dahuangyaRbacUserDto DahuangyaRbacUserDto
     *                             <p/>{
     *                             <p/>   "userName": "zhuying",  //登录账号
     *                             <p/>   "userPassword": "zhuying",  //登录密码
     *                             <p/>   "mobile": null,  //手机号
     *                             <p/>   "status": null,
     *                             <p/>   "type": null,
     *                             <p/>   "roleCode": null, //角色编码
     *                             <p/>   "remark": "助赢科技管理账号"  //备注
     *                             <p/>}
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/user/select")
    Object userSelect(@RequestBody @ApiContext DahuangyaRbacUserDto dahuangyaRbacUserDto) throws BusinessException {
        return dahuangyaRbacUserService.select(dahuangyaRbacUserDto);
    }

    /**
     * 操作员新增接口
     *
     * @param dahuangyaRbacUserDto DahuangyaRbacUserDto
     *                             <p/>{
     *                             <p/>   "userName": "zhuying",  //登录账号
     *                             <p/>   "userPassword": "zhuying",  //登录密码
     *                             <p/>   "mobile": null,  //手机号
     *                             <p/>   "status": null,
     *                             <p/>   "type": null,
     *                             <p/>   "roleCode": null, //角色编码
     *                             <p/>   "remark": "助赢科技管理账号"  //备注
     *                             <p/>}
     * @return JSONObject
     * <p/>{
     * <p/> "result": true,
     * <p/>  "value": 1   //成功的记录数
     * <p/>}
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/user/insert")
    Object userInsert(@RequestBody DahuangyaRbacUserDto dahuangyaRbacUserDto) throws BusinessException {
        dahuangyaRbacUserDto.setAppId(1);
        return dahuangyaRbacUserService.insert(dahuangyaRbacUserDto);
    }

    /**
     * 操作员删除接口
     *
     * @param dahuangyaRbacUserDto DahuangyaRbacUserDto
     *                             <p/>{
     *                             <p/>   "id": 1000000  //操作员id
     *                             <p/>}
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/user/delete")
    Object userDelete(@RequestBody DahuangyaRbacUserDto dahuangyaRbacUserDto) throws BusinessException {
        dahuangyaRbacUserDto.setAppId(1);
        return dahuangyaRbacUserService.delete(dahuangyaRbacUserDto);
    }

    /**
     * 操作员更新接口
     *
     * @param dahuangyaRbacUserDto DahuangyaRbacUserDto
     *                             <p/>{
     *                             <p/>   "id": 1000000,  //操作员id
     *                             <p/>   "userName": "zhuying",  //登录账号
     *                             <p/>   "userPassword": "zhuying",  //登录密码
     *                             <p/>   "mobile": null,  //手机号
     *                             <p/>   "status": null,
     *                             <p/>   "type": null,
     *                             <p/>   "roleCode": null, //角色编码
     *                             <p/>   "remark": "助赢科技管理账号"  //备注
     *                             <p/>}
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/user/update")
    Object userUpdateByPrimaryKey(@RequestBody DahuangyaRbacUserDto dahuangyaRbacUserDto) throws BusinessException {
        dahuangyaRbacUserDto.setAppId(1);
        return dahuangyaRbacUserService.updateByPrimaryKey(dahuangyaRbacUserDto);
    }

    /**
     * 操作日志查询接口
     *
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @NoNeedRight
    @RequestMapping("/user/select-all-login-history")
    Object userSelectAllLoginHistory() throws BusinessException {
        DahuangyaRbacLoginOperateLogDto dahuangyaRbacLoginOperateLogDto = new DahuangyaRbacLoginOperateLogDto();
        dahuangyaRbacLoginOperateLogDto.setLoginId(TraceUtil.getTokenObject() == null ? null : TraceUtil.getTokenObject().getLoginId());
        return dahuangyaRbacLoginService.selectAllLoginHistory(dahuangyaRbacLoginOperateLogDto);
    }

    //----------------测试--------
    @RequestMapping("/test/insert-exception")
    Object testInsertException(@RequestBody DahuangyaRbacUserDto dahuangyaRbacUserDto) throws BusinessException {
        return dahuangyaRbacUserService.insertException(dahuangyaRbacUserDto);
    }

    @RequestMapping("/test/no-need-login")
    @NoNeedLogin
    Object testNoNeedLogin() throws BusinessException {

        return "这个接口不需要登录也不需要授权即可访问";
    }

    @RequestMapping("/test/no-need-right")
    @NoNeedRight
    Object testNoNeedRight() throws BusinessException {

        return "这个接口不需要授权即可访问，但仍需要登录";
    }

    //----------------资源--------

    /**
     * 资源列表接口
     *
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @NoNeedRight
    @RequestMapping("/resource/select-all")
    Object resourceSelectAll() throws BusinessException {
        return dahuangyaRbacResourceService.selectAll();
    }

    /**
     * 资源列表接口[分页查询]
     *
     * @param pageObject 分页信息
     * @return JSONObject
     * @throws BusinessException
     */

    @RequestMapping("/resource/select-all-with-page")
    Object resourceSelectAllWithPage(@RequestBody PageObject pageObject) throws BusinessException {

        return dahuangyaRbacResourceService.selectAllWithPage(pageObject);
    }

    /**
     * 资源查询接口
     *
     * @param dahuangyaRbacResourceDto DahuangyaRbacResourceDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/resource/select")
    Object resourceSelect(@RequestBody DahuangyaRbacResourceDto dahuangyaRbacResourceDto) throws BusinessException {
        return dahuangyaRbacResourceService.select(dahuangyaRbacResourceDto);
    }

    /**
     * 资源新增接口
     *
     * @param dahuangyaRbacResourceDto DahuangyaRbacResourceDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/resource/insert")
    Object resourceInsert(@RequestBody DahuangyaRbacResourceDto dahuangyaRbacResourceDto) throws BusinessException {
        return dahuangyaRbacResourceService.insert(dahuangyaRbacResourceDto);
    }

    /**
     * 资源删除接口
     *
     * @param dahuangyaRbacResourceDto DahuangyaRbacResourceDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/resource/delete")
    Object resourceDelete(@RequestBody DahuangyaRbacResourceDto dahuangyaRbacResourceDto) throws BusinessException {
        return dahuangyaRbacResourceService.delete(dahuangyaRbacResourceDto);
    }

    /**
     * 资源更新接口
     *
     * @param dahuangyaRbacResourceDto DahuangyaRbacResourceDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/resource/update")
    Object resourceUpdateByPrimaryKey(@RequestBody DahuangyaRbacResourceDto dahuangyaRbacResourceDto) throws BusinessException {
        return dahuangyaRbacResourceService.updateByPrimaryKey(dahuangyaRbacResourceDto);
    }

    //----------------角色--------

    /**
     * 角色列表接口
     *
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/role/select-all")
    Object roleSelectAll() throws BusinessException {
        return dahuangyaRbacRoleService.selectAll();
    }

    /**
     * 角色查询接口
     *
     * @param dahuangyaRbacRoleDto DahuangyaRbacRoleDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/role/select")
    Object roleSelect(@RequestBody DahuangyaRbacRoleDto dahuangyaRbacRoleDto) throws BusinessException {
        dahuangyaRbacRoleDto.setAppId(1);
        return dahuangyaRbacRoleService.select(dahuangyaRbacRoleDto);
    }

    /**
     * 角色新增接口
     *
     * @param dahuangyaRbacRoleDto DahuangyaRbacRoleDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/role/insert")
    Object roleInsert(@RequestBody DahuangyaRbacRoleDto dahuangyaRbacRoleDto) throws BusinessException {
        dahuangyaRbacRoleDto.setAppId(1);
        return dahuangyaRbacRoleService.insert(dahuangyaRbacRoleDto);
    }

    /**
     * 角色删除接口
     *
     * @param dahuangyaRbacRoleDto DahuangyaRbacRoleDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/role/delete")
    Object roleDelete(@RequestBody DahuangyaRbacRoleDto dahuangyaRbacRoleDto) throws BusinessException {
        dahuangyaRbacRoleDto.setAppId(1);
        return dahuangyaRbacRoleService.delete(dahuangyaRbacRoleDto);
    }

    /**
     * 角色更新接口
     *
     * @param dahuangyaRbacRoleDto DahuangyaRbacRoleDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/role/update")
    Object roleUpdateByPrimaryKey(@RequestBody DahuangyaRbacRoleDto dahuangyaRbacRoleDto) throws BusinessException {
        dahuangyaRbacRoleDto.setAppId(1);
        return dahuangyaRbacRoleService.updateByPrimaryKey(dahuangyaRbacRoleDto);
    }


    //----------------会员--------

    /**
     * 会员列表接口
     *
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/member/select-all")
    Object memberSelectAll() throws BusinessException {
        return dahuangyaRbacMemberService.selectAll();
    }

    /**
     * 会员查询接口
     *
     * @param dahuangyaRbacMemberDto DahuangyaRbacMemberDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/member/select")
    Object memberSelect(@RequestBody DahuangyaRbacMemberDto dahuangyaRbacMemberDto) throws BusinessException {
        dahuangyaRbacMemberDto.setAppId(1);
        return dahuangyaRbacMemberService.select(dahuangyaRbacMemberDto);
    }

    /**
     * 会员新增接口
     *
     * @param dahuangyaRbacMemberDto DahuangyaRbacMemberDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/member/insert")
    Object memberInsert(@RequestBody DahuangyaRbacMemberDto dahuangyaRbacMemberDto) throws BusinessException {
        dahuangyaRbacMemberDto.setAppId(1);
        return dahuangyaRbacMemberService.insert(dahuangyaRbacMemberDto);
    }

    /**
     * 会员删除接口
     *
     * @param dahuangyaRbacMemberDto DahuangyaRbacMemberDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/member/delete")
    Object memberDelete(@RequestBody DahuangyaRbacMemberDto dahuangyaRbacMemberDto) throws BusinessException {
        dahuangyaRbacMemberDto.setAppId(1);
        return dahuangyaRbacMemberService.delete(dahuangyaRbacMemberDto);
    }

    /**
     * 会员更新接口
     *
     * @param dahuangyaRbacMemberDto DahuangyaRbacMemberDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/member/update")
    @NoNeedRight
    Object memberUpdateByPrimaryKey(@RequestBody DahuangyaRbacMemberDto dahuangyaRbacMemberDto) throws BusinessException {
        Token token = JSONObject.parseObject(TraceUtil.getTokenStr(),Token.class);
        dahuangyaRbacMemberDto.setAppId(token.getAppId());
        return dahuangyaRbacMemberService.updateByPrimaryKey(dahuangyaRbacMemberDto);
    }

    /**
     * 微信用户开放数据回写
     * @param weixinUserInfoVo
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/member/update-user-info")
    @NoNeedRight
    Object memberUpdateUserInfoByPrimaryKey(@RequestBody WeixinUserInfoVo weixinUserInfoVo) throws BusinessException {

        Token token = JSONObject.parseObject(TraceUtil.getTokenStr(),Token.class);

        DahuangyaRbacMemberDto dahuangyaRbacMemberDto = new DahuangyaRbacMemberDto();
        dahuangyaRbacMemberDto.setId(token.getLoginId());
        dahuangyaRbacMemberDto.setAppId(token.getAppId());
        dahuangyaRbacMemberDto.setNickName(weixinUserInfoVo.getUserInfo().getNickName());
        dahuangyaRbacMemberDto.setAvatarUrl(weixinUserInfoVo.getUserInfo().getAvatarUrl());
        dahuangyaRbacMemberDto.setGender(weixinUserInfoVo.getUserInfo().getGender());
        dahuangyaRbacMemberDto.setCountry(weixinUserInfoVo.getUserInfo().getCountry());
        dahuangyaRbacMemberDto.setProvince(weixinUserInfoVo.getUserInfo().getProvince());
        dahuangyaRbacMemberDto.setCity(weixinUserInfoVo.getUserInfo().getCity());
        return dahuangyaRbacMemberService.updateByPrimaryKey(dahuangyaRbacMemberDto);
    }




    //----------------模块与菜单--------

    /**
     * 菜单列表接口
     *
     * @return JSONObject
     * @throws BusinessException 业务异常
     */

    @RequestMapping("/module/select-all")
    @NoNeedRight
    Object moduleSelectAll() throws BusinessException {
        return dahuangyaRbacModuleService.selectAll();
    }

    /**
     * 菜单查询接口
     *
     * @param dahuangyaRbacModuleDto DahuangyaRbacModuleDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/module/select")
    Object moduleSelect(@RequestBody DahuangyaRbacModuleDto dahuangyaRbacModuleDto) throws BusinessException {
        return dahuangyaRbacModuleService.select(dahuangyaRbacModuleDto);
    }

    /**
     * 菜单新增接口
     *
     * @param dahuangyaRbacModuleDto DahuangyaRbacModuleDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/module/insert")
    Object moduleInsert(@RequestBody DahuangyaRbacModuleDto dahuangyaRbacModuleDto) throws BusinessException {
        //插入
        return dahuangyaRbacModuleService.insert(dahuangyaRbacModuleDto);
    }

    /**
     * 菜单删除接口
     *
     * @param dahuangyaRbacModuleDto DahuangyaRbacModuleDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/module/delete")
    Object moduleDelete(@RequestBody DahuangyaRbacModuleDto dahuangyaRbacModuleDto) throws BusinessException {
        return dahuangyaRbacModuleService.delete(dahuangyaRbacModuleDto);
    }

    /**
     * 菜单更新接口
     *
     * @param dahuangyaRbacModuleDto DahuangyaRbacModuleDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/module/update")
    Object moduleUpdateByPrimaryKey(@RequestBody DahuangyaRbacModuleDto dahuangyaRbacModuleDto) throws BusinessException {
        return dahuangyaRbacModuleService.updateByPrimaryKey(dahuangyaRbacModuleDto);
    }

    //----------------部门--------

    /**
     * 部门列表接口
     *
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/department/select-all")
    Object departmentSelectAll() throws BusinessException {
        return dahuangyaRbacDepartmentService.selectAll();
    }

    /**
     * 部门查询接口
     *
     * @param dahuangyaRbacDepartmentDto DahuangyaRbacDepartmentDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/department/select")
    Object departmentSelect(@RequestBody DahuangyaRbacDepartmentDto dahuangyaRbacDepartmentDto) throws BusinessException {
        dahuangyaRbacDepartmentDto.setAppId(1);
        return dahuangyaRbacDepartmentService.select(dahuangyaRbacDepartmentDto);
    }

    /**
     * 部门新增接口
     *
     * @param dahuangyaRbacDepartmentDto DahuangyaRbacDepartmentDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/department/insert")
    Object departmentInsert(@RequestBody DahuangyaRbacDepartmentDto dahuangyaRbacDepartmentDto) throws BusinessException {
        dahuangyaRbacDepartmentDto.setAppId(1);
        return dahuangyaRbacDepartmentService.insert(dahuangyaRbacDepartmentDto);
    }

    /**
     * 部门删除接口
     *
     * @param dahuangyaRbacDepartmentDto DahuangyaRbacDepartmentDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/department/delete")
    Object departmentDelete(@RequestBody DahuangyaRbacDepartmentDto dahuangyaRbacDepartmentDto) throws BusinessException {
        dahuangyaRbacDepartmentDto.setAppId(1);
        return dahuangyaRbacDepartmentService.delete(dahuangyaRbacDepartmentDto);
    }

    /**
     * 部门更新接口
     *
     * @param dahuangyaRbacDepartmentDto DahuangyaRbacDepartmentDto
     * @return JSONObject
     * @throws BusinessException 业务异常
     */
    @RequestMapping("/department/update")
    Object departmentUpdateByPrimaryKey(@RequestBody DahuangyaRbacDepartmentDto dahuangyaRbacDepartmentDto) throws BusinessException {
        dahuangyaRbacDepartmentDto.setAppId(1);
        return dahuangyaRbacDepartmentService.updateByPrimaryKey(dahuangyaRbacDepartmentDto);
    }

    //----------------app--------


}
