package com.example.mmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedirectHandler {

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }
    @GetMapping("/")
    public  String main(){
        return "redirect:/list";
    }
}
