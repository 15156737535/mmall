package com.example.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mmall.entity.*;
import com.example.mmall.mapper.*;
import com.example.mmall.mapper.OrderMapper;
import com.example.mmall.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private CartMapper cartMapper;
    @Override
    public boolean save(Orders orders, User user,String address,String remark) {

        if(orders.getUserAddress().equals("newAddress")){
            //存入数据库
            UserAddress userAddress = new UserAddress();
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddress.setIsdefault(1);
            userAddress.setUserId(user.getId());

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("isdefault",1);
            UserAddress oldDefault = userAddressMapper.selectOne(wrapper);
            oldDefault.setIsdefault(0);
            userAddressMapper.updateById(oldDefault);
            userAddressMapper.insert(userAddress);
            orders.setUserAddress(address);
        }
        //存储orders
        orders.setUserId(user.getId());
        orders.setLoginName(user.getLoginName());
        String seriaNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < 32; i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber = result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setSerialnumber(seriaNumber);
        orderMapper.insert(orders);
        QueryWrapper queryWrapper=new QueryWrapper();
     queryWrapper.eq("user_id",user.getId());
        List<Cart> cartList=cartMapper.selectList(queryWrapper);
        for (Cart cart : cartList) {
            OrderDetail orderDetail=new OrderDetail();
            BeanUtils.copyProperties(cart,orderDetail);
            orderDetail.setId(null);
            orderDetail.setOrderId(orders.getId());
            orderDetailMapper.insert(orderDetail);
        }
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("user_id",user.getId());
        cartMapper.delete(wrapper1);
      return  true;
    }
}
