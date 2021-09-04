package com.example.mmall.vo;

import lombok.Data;

@Data
public class CartVo {
    private Integer id;
    private Integer quantity;
    private Float cost;
    private String name;
    private Float price;
    private Integer productId;
    private String fileName;
    private Integer stock;
}
