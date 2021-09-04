package com.example.mmall.controller;


import com.example.mmall.entity.User;
import com.example.mmall.service.CartService;
import com.example.mmall.service.impl.ProductCategoryServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */
@Controller

public class ProductCategoryController {
    @Autowired
    private ProductCategoryServiceImpl productCategoryService;
    @Autowired
    CartService cartService;

    @GetMapping("/list")
    public ModelAndView list(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        User user = (User) httpSession.getAttribute("user");
        modelAndView.addObject("list", productCategoryService.getAllProductCategoryVo());
        if(user==null){
            modelAndView.addObject("carts", new ArrayList<>());

        }else {
            modelAndView.addObject("carts", cartService.findAllCartVoByUserId(user.getId()));
        }
        return modelAndView;
    }

}

