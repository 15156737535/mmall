package com.example.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mmall.entity.Cart;
import com.example.mmall.entity.User;
import com.example.mmall.service.CartService;
import com.example.mmall.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

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

@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
  @Autowired
    UserAddressService userAddressService;
    @GetMapping("/add/{productId}/{price}/{quantity}")
    public String add(@PathVariable("productId") Integer productId,
                      @PathVariable("price") Float price,
                      @PathVariable("quantity") Integer quantity, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setCost(price * quantity);

        User    user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        try {
            if (cartService.save(cart)) {
                return "redirect:/cart/findAllCart";
            }
        } catch (Exception e) {
            return "redirect:/list";
        }

        return null;
    }

    @GetMapping("/findAllCart")
    public ModelAndView findAll(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        if (user == null) {

            modelAndView.setViewName("login");
        } else {
            modelAndView.addObject("carts", cartService.findAllCartVoByUserId(user.getId()));
            modelAndView.setViewName("settlement1");
        }
        return modelAndView;


    }
    @GetMapping("/deleteById/{id}")
    public String  deleteById( @PathVariable("id") Integer id){
       cartService.removeById(id);
        return "redirect:/cart/findAllCart";
    }
    @GetMapping("/settlement2")
    public ModelAndView settlement2(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        if (user == null) {

            modelAndView.setViewName("login");
        } else {
            modelAndView.addObject("carts", cartService.findAllCartVoByUserId(user.getId()));
            QueryWrapper wrapper=new QueryWrapper();
            wrapper.eq("user_id",user.getId());
            modelAndView.addObject("addressList", userAddressService.list(wrapper));
            modelAndView.setViewName("settlement2");
        }
        return modelAndView;


    }
    @ResponseBody
    @PostMapping("updateCart/{id}/{quantity}/{cost}")
    public String updateCart(@PathVariable("id") Integer id, @PathVariable("quantity") Integer quantity,@PathVariable("cost") Float cost){
        Cart cart=cartService.getById(id);
        cart.setQuantity(quantity);
        cart.setCost(cost);
        if (cartService.updateById(cart)) {
            return "success";
        }
       else {
           return "false";
        }
    }
}



