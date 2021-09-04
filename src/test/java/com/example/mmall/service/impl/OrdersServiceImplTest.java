package com.example.mmall.service.impl;

import com.example.mmall.entity.Orders;
import com.example.mmall.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class OrdersServiceImplTest {
    @Autowired
    OrderService orderService;

 @Test
    void  test(){
     Orders orders =new Orders();
     orders.setUserId(1);
     orderService.save(orders);

 }
}