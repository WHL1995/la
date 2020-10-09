package com.szlaun.launtech.system.menu.controller;

import com.szlaun.launtech.anno.Authority;
import com.szlaun.launtech.system.menu.dto.Menu;
import com.szlaun.launtech.system.menu.service.MenuService;
import com.szlaun.launtech.system.role.dto.RoleMenuKey;
import com.szlaun.launtech.system.role.mapper.RoleMenuMapper;
import com.szlaun.launtech.system.user.dto.User;
import com.szlaun.launtech.utils.Constant;
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
 * @Date 2020/10/9 10:29
 * @Version V1.0
 **/
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * 查询所有用户
     * @param id    用户ID或者角色ID根据类型定义
     * @param type  取值（
     *              1：依据 角色 ID查询 菜单 集合
     *              2：依据 用户 ID查询 菜单 集合
     *              3：依据 角色 ID查询 按钮 集合
     *              4：依据 用户 ID查询 按钮 集合
     *              ）
     * @return
     */
    @ResponseBody
    @Authority("user:select")
    @RequestMapping("/select")
    public ResultMsg select(@RequestParam(required = true) String id,@RequestParam(required = true) int type){
        List<Menu> menus = null;
        switch (type){
            case 1:
                menus = menuService.selectMenuByRoleId(id);
                break;
            case 2:
                menus = menuService.selectMenuByUserId(id);
                break;
            case 3:
                menus = menuService.selectButtonByRoleId(id);
                break;
            case 4:
                menus = menuService.selectButtonByUserId(id);
                break;
        }
        return ResultMsg.getSuccess("操作成功",menus);
    }

    /**
     * 修改菜单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @Authority({"menu:update", "menu:select"})
    @RequestMapping("/update")
    public ResultMsg update(HttpServletRequest request, @RequestParam(required = true) Menu menu) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (menu != null && user != null) {
            menu.setUpdateUser(user.getId());
            int result = menuService.update(menu);
            if(result > 0){
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    /**
     * 添加菜单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @Authority({"menu:insert", "menu:select"})
    @RequestMapping("/insert")
    public ResultMsg insert(HttpServletRequest request,@RequestParam(required = true) Menu menu) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (menu != null && user != null) {
            menu.setUpdateUser(user.getId());
            int result = menuService.insert(menu);
            if(result > 0){
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    /**
     * 移除菜单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @Authority({"menu:delete", "menu:select"})
    @RequestMapping("/delete")
    public ResultMsg delete(HttpServletRequest request,@RequestParam(required = true) String menuId) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (!StringUtils.isEmpty(menuId) && user != null) {
            int result = menuService.delete(menuId);
            if(result > 0){
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    /**
     * 给角色添加菜单关系
     *
     * @param request
     * @return
     */
    @ResponseBody
    @Authority({"menu:insert", "menu:select"})
    @RequestMapping("/setRoleMenu")
    public ResultMsg setRoleMenu(HttpServletRequest request,@RequestParam(required = true)RoleMenuKey roleMenuKey){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (roleMenuKey != null && user != null) {
            int result = roleMenuMapper.insertSelective(roleMenuKey);
            if(result > 0){
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    /**
     * 给角色添加菜单关系(批量)
     *
     * @param
     * @return
     */
    @ResponseBody
    @Authority({"menu:insert", "menu:select"})
    @RequestMapping("/setRoleMenuAll")
    public ResultMsg setRoleMenuAll(@RequestParam(required = true)List<RoleMenuKey> roleMenuKeys){
        if (roleMenuKeys != null && roleMenuKeys.size() != 0) {
            int result = roleMenuMapper.batchRoleMenu(roleMenuKeys);
            if(result > 0){
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    /**
     * 移出角色菜单关系(批量)
     *
     * @param
     * @return
     */
    @ResponseBody
    @Authority({"menu:delete", "menu:select"})
    @RequestMapping("/removeRoleMenuAll")
    public ResultMsg removeRoleMenuAll(@RequestParam(required = true)List<RoleMenuKey> roleMenuKeys){
        if (roleMenuKeys != null && roleMenuKeys.size() != 0) {
            int result = 0;
            for (RoleMenuKey item:roleMenuKeys) {
                result += roleMenuMapper.deleteByPrimaryKey(item);
            }
            if(result == roleMenuKeys.size()){
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }
}
