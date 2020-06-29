package com.practice.controller;

import com.practice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.provider.url}")
    private String url;

    /*
    * 注册功能
    * */
    @RequestMapping("/addregister")
    public String register(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String userPhone = request.getParameter("userPhone");

//        String result = restTemplate.postForObject("http://localhost:9090/addregister?username="+username+"&password="+password+"&password2="+password2+"&userPhone="+userPhone,null, String.class);
        String result = restTemplate.postForObject(url+"/addregister?username="+username+"&password="+password+"&password2="+password2+"&userPhone="+userPhone,null, String.class);
        if ("success".equals(result)){  //登录成功
            return "denglu";
        }else{
            return "register";
        }

    }


    /*
    * 登录功能
    * */
    @RequestMapping("/addlogin")
    public String login(HttpServletRequest request,
                              HttpServletResponse response,
                              Map<String,Object> map,
                              Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User dbUser = restTemplate.postForObject(
                url+"/addlogin?username=" + username + "&password=" + password, null, User.class);
        HttpSession session = request.getSession();
        if (dbUser != null){  //user不为空，说明用户合法，进行更新操作
            String token = UUID.randomUUID().toString();
            dbUser.setToken(token);
            restTemplate.postForObject(url+"/updateUser",dbUser, Integer.class);
            Cookie cookie=new Cookie("token",token);
            cookie.setMaxAge(60*60*24*7);       //设置cookie保存时间为一周
            response.addCookie(cookie);
            session.setAttribute("user", dbUser);
            map.put("msg", "登陆成功");
            return "redirect:/";
        }else{
            map.put("msg","密码或用户名错误！");
            return "denglu";
        }

    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
                               HttpServletResponse response){
        request.getSession().removeAttribute("user");   //移除session
        Cookie cookie = new Cookie("token", null);  //要删除cookie需要新建一个同名的cookie，并将value设置为null
        cookie.setMaxAge(0);    //立即删除型
        response.addCookie(cookie);//删除cookie
        return "redirect:/";

    }


}
