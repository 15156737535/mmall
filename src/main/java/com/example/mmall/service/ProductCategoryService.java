package com.example.mmall.service;

import com.example.mmall.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mmall.vo.ProductCategoryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProductCategoryVo> getAllProductCategoryVo();
}
