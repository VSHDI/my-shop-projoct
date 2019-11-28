package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface TbUserService {

    /**
     * 查询全部用户信息
     * @return
     */
    public List<TbUser> selectAll();

    BaseResult save(TbUser tbUser);

    void deleteById(Long id);

    void delete(TbUser tbUser);

    TbUser getById(Long id);

    void update(TbUser tbUser);

    List<TbUser> selectByName(String username);

    TbUser login(String email, String password);

    /**
     * 搜索功能
     * @param keyword
     * @return
     */
    List<TbUser> search(String keyword);
}