package com.szlaun.launtech.system.user.controller;

import com.szlaun.launtech.anno.Authority;
import com.szlaun.launtech.enums.PropertyAddFlagEnum;
import com.szlaun.launtech.system.user.dto.User;
import com.szlaun.launtech.system.user.dto.UserRoleKey;
import com.szlaun.launtech.system.user.mapper.UserRoleMapper;
import com.szlaun.launtech.system.user.service.UserService;
import com.szlaun.launtech.utils.Constant;
import com.szlaun.launtech.utils.PropertyUtils;
import com.szlaun.launtech.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description
 * @Author lizhiming
 * @Date 2020/10/9 10:12
 * @Version V1.0
 **/
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     *
     * @param
     * @return
     */
    @ResponseBody
    @Authority("user:select")
    @RequestMapping("/select")
    public ResultMsg select(){
        List<User> users = userService.selectAll();
        return ResultMsg.getSuccess("操作成功",users);
    }

    /**
     * 修改密码
     *
     * @param request
     * @return
     */
    @ResponseBody
    @Authority({"user:update", "user:select"})
    @RequestMapping("/updatePassword")
    public ResultMsg updatePassword(HttpServletRequest request, @RequestParam(required = true) String password) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (!StringUtils.isEmpty(password) && user != null) {
            user.setPassword(password);
            int result = userService.updateByPrimaryKeySelective(user);
            if(result > 0){
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    /**
     * 添加用户
     *
     * @param request
     * @return
     */
    @ResponseBody
    @Authority({"user:insert", "user:select"})
    @RequestMapping("/insert")
    public ResultMsg insert(HttpServletRequest request,@RequestParam(required = true) User user) {
        User createUser = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (createUser != null && user != null) {
            PropertyUtils.addDefaultProperty(user, PropertyAddFlagEnum.INSERT,createUser.getId());
            int result = userService.insert(user);
            if(result > 0){
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    /**
     * 移除用户
     *
     * @param request
     * @return
     */
    @ResponseBody
    @Authority({"user:delete", "user:select"})
    @RequestMapping("/delete")
    public ResultMsg delete(HttpServletRequest request,@RequestParam(required = true) String id) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (!StringUtils.isEmpty(id) && user != null) {
            int result = userService.deleteByPrimaryKey(id);
            if(result > 0){
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    /**
     * 给用户设置角色
     *
     * @param request
     * @return
     */
    @ResponseBody
    @Authority({"user:delete", "user:insert"})
    @RequestMapping("/setUserRole")
    public ResultMsg setUserRole(HttpServletRequest request,@RequestParam(required = true) UserRoleKey userRoleKey) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (userRoleKey != null && user != null) {
            userRoleKey.setCreateUser(user.getId());
            int result = userRoleMapper.insertSelective(userRoleKey);
            if(result > 0){
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    /**
     * 给用户移出角色
     *
     * @param request
     * @return
     */
    @ResponseBody
    @Authority({"user:delete", "user:select"})
    @RequestMapping("/removerUserRole")
    public ResultMsg removerUserRole(HttpServletRequest request,@RequestParam(required = true) UserRoleKey userRoleKey) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (userRoleKey != null && user != null) {
            int result = userRoleMapper.deleteByPrimaryKey(userRoleKey);
            if(result > 0){
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

}
