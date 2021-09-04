package com.example.mmall.controller;


import com.example.mmall.entity.Orders;
import com.example.mmall.entity.User;
import com.example.mmall.service.CartService;
import com.example.mmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */
@Controller
@RequestMapping("//orders")
public class OrderController {
    @Autowired
    OrderService orderService;
@Autowired
CartService cartService;
    @PostMapping("/settlement3")
    public ModelAndView settlement3(String userAddress, Float cost, HttpSession httpSession,
                                    String address,String remark) {
        Orders orders = new Orders();
        orders.setCost(cost);
        orders.setUserAddress(userAddress);
        User user = (User) httpSession.getAttribute("user");
     orderService.save(orders, user,address,remark);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("carts", cartService.findAllCartVoByUserId(user.getId()));
        modelAndView.setViewName("settlement3");
        return modelAndView;
    }
}

