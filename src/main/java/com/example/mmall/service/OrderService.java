package com.example.mmall.service;

import com.example.mmall.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mmall.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */
public interface OrderService extends IService<Orders> {
    public  boolean save(Orders orders, User user,String address,String remark) ;

}
