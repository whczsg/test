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

    //更新用户基本信息
    void update(User user);

    //更新头像
    void updateAvatar(String avatarUrl);

    //更新密码
    void updatePwd(String newPwd);
}
