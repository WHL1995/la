package com.szlaun.launtech.handler;

import com.alibaba.fastjson.JSONObject;
import com.szlaun.launtech.anno.Authority;
import com.szlaun.launtech.service.UserService;
import com.szlaun.launtech.system.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @Description 拦截器
 * @Author lizhiming
 * @Date 2020/9/5 10:32
 * @Version V1.0
 **/
@Component
public class ActionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authority authority = null;
        String message = "Sorry, you don't have access.";
        try {
            /** 查看拦截的方法是否有@Authority注解 */
            HandlerMethod method = (HandlerMethod) handler;
            /** 获取方法上的注解实列 */
            authority = method.getMethodAnnotation(Authority.class);
        } catch (Exception e) {
            /** 不是拦截目标放行 */
            return true;
        }
        if (authority != null) {
            /** 取出方法所需权限 */
            String[] values = authority.value();
            User user = (User) request.getSession().getAttribute("user");
            if(user != null){
                int result = userService.verifyPermission(user.getId(),Arrays.asList(values));
                /* 必须满足所有的权限 */
                if (result != values.length){
                    responseMessage(response,message);
                    return false;
                }
            }else{
                responseMessage(response,message);
                return false;
            }
            //#region 弃用的方式，如果需要使用可以以这个代码作为参照
//            boolean isAnd = power.isAnd();
//            UserDTO myUser = shiroServiceFeign.getMyUser();
//            String[] roleIds = myUser.getRoleIds();
//            if (roleIds == null || roleIds.length < 1) {
//                return false;
//            }
//            List<Map<String, Object>> maps = new ArrayList<>();
//            RoleDTO role = new RoleDTO();
//            for (String item : roleIds) {
//                role.setId(item);
//                List<Map<String, Object>> map = shiroServiceFeign.doRoleMenuTreeData(role);
//                maps.addAll(map);
//            }
//            if (maps.size() < 1) return false;
//            String powers = "";
//            List<Object> names = maps.stream().map(e -> e.get("name")).collect(Collectors.toList());
//            for (Object obj : names) {
//                powers += (String) obj;
//            }
//            boolean result = false;
//            /* 是否需要多个权限 */
//            if (isAnd && values.length > 1) {
//                for (int i = 0; i < values.length; i++) {
//                    if (powers.contains(values[i])) {
//                        result = true;
//                    } else {
//                        result = false;
//                        break;
//                    }
//                }
//                if (!result) responseMessage(response);
//                return result;
//            } else {
//                boolean flage = powers.contains(values[0]);
//                if (!flage) responseMessage(response);
//                return flage;
//            }
            //#regionend
        }
        return true;
    }

    /**
     * @param response
     * @return void
     * @Description: 响应无权限的消息
     * @Author Zhiming Li
     * @Date 2020/5/16
     * @Version V1.0
     */
    public static void responseMessage(HttpServletResponse response, String message) throws Exception {
        //重置response
        response.reset();
        //设置编码格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("error", message);
        pw.write(json.toJSONString());
        pw.flush();
        pw.close();
    }
}
