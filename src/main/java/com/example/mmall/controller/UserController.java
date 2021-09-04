package com.example.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mmall.entity.User;
import com.example.mmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(String loginName, String password, HttpSession session) {
        QueryWrapper wrapper= new QueryWrapper();
        wrapper.eq("login_name",loginName);
        wrapper.eq("password",password);

        User user =userService.getOne(wrapper);
        if(user==null){
            return "login";
        }else {
            session.setAttribute("user",user);

            return "redirect:/list";

        }
    }
    @GetMapping("logout")
public  String logout(HttpSession session){
        session.invalidate();;
        return "login";
    }
    @PostMapping("/register")
    public String register(User user, Model model){
        boolean result= false;
        try {
            result = userService.save(user);
        } catch (Exception e) {
          model.addAttribute("error","用户名"+user.getLoginName()+"已存在!请重新输入");
        }
        if(result) return "login";
        return "register";
    }


}

