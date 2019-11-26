package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 登录
     * @param email
     * @param password
     * @return
     */
    User login(String email, String password);
}

