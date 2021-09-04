package com.example.mmall.service.impl;

import com.example.mmall.entity.User;
import com.example.mmall.mapper.UserMapper;
import com.example.mmall.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
