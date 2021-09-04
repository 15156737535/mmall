package com.example.mmall.service;

import com.example.mmall.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mmall.vo.CartVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */
public interface  CartService extends IService<Cart> {
 public List<CartVo> findAllCartVoByUserId(Integer id);
}
