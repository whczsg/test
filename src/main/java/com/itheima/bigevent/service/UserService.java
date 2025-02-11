package com.itheima.bigevent.service;

import com.itheima.bigevent.pojo.User;

/*
用户服务类
 */
public interface UserService {
    //根据用户名查询用户
    User findByUserName(String username);

    //注册
    void register(String username, String password);
}
