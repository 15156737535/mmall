package com.example.mmall.service;

import com.example.mmall.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */
public interface ProductService extends IService<Product> {
        public List<Product> findByCategoryId(String type,Integer categoryId);
}
