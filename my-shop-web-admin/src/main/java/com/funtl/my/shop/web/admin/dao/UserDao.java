package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.User;

public interface UserDao {
    /**
     * 根据邮箱和密码查询用户信息
     * @param password
     * @param email
     * @return user
     */
    User getUser(String email,String password);
}

