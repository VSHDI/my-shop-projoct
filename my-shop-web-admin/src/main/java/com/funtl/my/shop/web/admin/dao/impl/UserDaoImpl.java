package com.funtl.my.shop.web.admin.dao.impl;

import com.funtl.my.shop.domain.User;
import com.funtl.my.shop.web.admin.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDao {

    //    private static final Logger logger = Logger.getLogger(String.valueOf(UserDaoImpl.class));
    public User getUser(String email, String password) {

        User user = null;

        if ("804657960@qq.com".equals(email)) {
            if ("123456".equals(password)) {
                user = new User();
                user.setEmail(email);
                user.setUsername("VSHDI");

                System.out.println("succuse--" + user.getUsername());
            }
        }else {
            System.out.println("fail--"+email);
        }
        return user;

    }
}
