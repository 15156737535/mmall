package com.example.mmall.service.impl;

import com.example.mmall.service.UserAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserAddressServiceImplTest {
@Autowired
UserAddressService userAddressService;
@Test
    void  test(){
    Map<String ,Object> map=new HashMap<>();
    map.put("user_id",10);
    userAddressService.listByMap(map).forEach(System.out::println);
}
}