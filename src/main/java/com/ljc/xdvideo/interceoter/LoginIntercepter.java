package com.ljc.xdvideo.interceoter;

import com.google.gson.Gson;
import com.ljc.xdvideo.domain.JsonData;
import com.ljc.xdvideo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 功能描述:用户登录拦截器
 *
 * @author linjiacheng2001
 * @date 2019-03-01 15:14
 */
public class LoginIntercepter implements HandlerInterceptor {

    private static final Gson gson = new Gson();

    /**
     * 进入controller之前进行拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }
        if (token != null) {
            Claims claims = JwtUtils.checkToken(token);
            if (claims != null) {
                Integer userId = (Integer) claims.get("id");
                String name = (String) claims.get("name");
                request.setAttribute("user_id", userId);
                request.setAttribute("name", name);
                return true;
            }
        }
        sendJsonMessage(response, JsonData.buildError("请登录"));

        return false;
    }


    /**
     * 响应数据给前端
     *
     * @param response
     * @param object
     */
    public static void sendJsonMessage(HttpServletResponse response, Object object) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(gson.toJson(object));
        printWriter.close();
        try {
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
