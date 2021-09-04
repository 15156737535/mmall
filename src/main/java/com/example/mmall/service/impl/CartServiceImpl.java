package com.example.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mmall.Exception.MyException;
import com.example.mmall.entity.Cart;
import com.example.mmall.entity.Product;
import com.example.mmall.enums.ResultEnum;
import com.example.mmall.mapper.CartMapper;
import com.example.mmall.mapper.ProductMapper;
import com.example.mmall.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mmall.vo.CartVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
@Autowired
    private  CartMapper cartMapper;
@Autowired
    private ProductMapper productMapper;

@Override
    public boolean save(Cart entity){
    Product product =productMapper.selectById(entity.getProductId());
    Integer stock=product.getStock()-entity.getQuantity();
    if(stock<0){
        log.error("添加购物车失败,愿意库存不足!stock={}");
        throw new MyException(ResultEnum.STOCK_ERROR);
    }
    product.setStock(stock);
    productMapper.updateById(product);
    if(cartMapper.insert(entity)==1){
        return true;
    }else

    {
        return  false;
    }
}

    @Override
    public List<CartVo> findAllCartVoByUserId(Integer id) {
      List<CartVo> cartList =new ArrayList<>();
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",id);
        List<Cart> carts=cartMapper.selectList(queryWrapper);
        for (Cart cart : carts) {
            CartVo cartVo =new CartVo();
            BeanUtils.copyProperties(cart,cartVo);
            Product product =productMapper.selectById(cart.getProductId());
            BeanUtils.copyProperties(product,cartVo);

            BeanUtils.copyProperties(cart,cartVo);
            cartList.add(cartVo);
        }
   return cartList;
    }
}
