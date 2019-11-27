package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface TbUserService {

    /**
     * 查询全部用户信息
     * @return
     */
    public List<TbUser> selectAll();

    BaseResult save(TbUser tbUser, HttpSession session);
    BaseResult update(TbUser tbUser, HttpSession session);



    void deleteById(Long id);

    void delete(TbUser tbUser);

    TbUser getById(Long id);

    void update(TbUser tbUser);

    List<TbUser> selectByName(String username);

    TbUser login(String email, String password);
}