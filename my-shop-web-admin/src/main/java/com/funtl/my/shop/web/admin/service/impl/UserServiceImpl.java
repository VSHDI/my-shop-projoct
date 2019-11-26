package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.domain.User;
import com.funtl.my.shop.web.admin.dao.UserDao;
import com.funtl.my.shop.web.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String email, String password) {

        User user = userDao.getUser(email,password);

        if (user.getEmail().equals("804657960@qq.com")) {
            if (user.getPassword().equals("123456")) {
                user.setUsername("VSHDI");
            }
        }
        return user;
    }
}
