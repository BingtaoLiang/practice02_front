package com.practice.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SessionInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Cookie[] cookies = request.getCookies();//服务器端获取客户端所有cookie
//        if (cookies != null && cookies.length != 0)
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    String token = cookie.getValue();
//                    User user = userMapper.findByToken(token);
//                    if (user == null) {
//                        request.getRequestDispatcher("/login").forward(request, response);
//                        return false;
//                    }else{
//                        request.getSession().setAttribute("user", user);//获取session对象并设置session中的属性
//                        return true;
//                    }
//                }
//            }
//        request.getRequestDispatcher("/login").forward(request, response);
//        return false;


//        Cookie[] cookies = request.getCookies();//服务器端获取客户端所有cookie
//        if (cookies != null && cookies.length != 0)
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    String token = cookie.getValue();
//                    User user = userMapper.findByToken(token);
//                    if (user != null) {
//                        request.getSession().setAttribute("user", user);//获取session对象并设置session中的属性
//                    }
//                    break;
//                }
//            }
//        return true;



        Object user = request.getSession().getAttribute("user");
        //获取session中的user参数，如果user为空，表示当前未登录，跳转回登录页面，否则放行访问
        if (user == null){
            request.setAttribute("msg","无权限，请先登录");
            request.getRequestDispatcher("/denglu").forward(request, response);
            return false;
//            return true;
        }else{
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
