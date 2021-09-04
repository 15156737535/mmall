package com.example.mmall.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
@Data

public class ProductCategoryVo {
    private  Integer id;
    private String name;
    private List<ProductCategoryVo> children;
    private  String bannerImg;
    private String topImg;
    private  List<ProductVo> productVo;

    public ProductCategoryVo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
