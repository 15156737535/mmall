package com.example.mmall.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductCategoryServiceImplTest {
  @Autowired ProductCategoryServiceImpl productCategoryService;
@Test
    public void test1() {
       productCategoryService.getAllProductCategoryVo();
    }
}