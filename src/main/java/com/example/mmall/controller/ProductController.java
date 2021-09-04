package com.example.mmall.controller;


import com.example.mmall.entity.User;
import com.example.mmall.service.CartService;
import com.example.mmall.service.ProductCategoryService;
import com.example.mmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/list/{type}/{id}")
    public ModelAndView list(@PathVariable("type") String type, @PathVariable("id") Integer id, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        User user = (User) httpSession.getAttribute("user");
        modelAndView.addObject("list", productCategoryService.getAllProductCategoryVo());
        modelAndView.addObject("product", productService.findByCategoryId(type, id));
        if(user==null){
            modelAndView.addObject("carts", new ArrayList<>());

        }else {
            modelAndView.addObject("carts", cartService.findAllCartVoByUserId(user.getId()));
        }
        return modelAndView;
    }

    @GetMapping("findById/{id}")
    public ModelAndView findById(@PathVariable("id") Integer id, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetail");
        User user = (User) httpSession.getAttribute("user");
        modelAndView.addObject("product", productService.getById(id));
        modelAndView.addObject("list", productCategoryService.getAllProductCategoryVo());
        if(user==null){
            modelAndView.addObject("carts", new ArrayList<>());

        }else {
            modelAndView.addObject("carts", cartService.findAllCartVoByUserId(user.getId()));
        }
        return modelAndView;
    }
}