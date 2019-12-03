package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
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
     * @param tbUser
     * @return
     */
    List<TbUser> search(TbUser tbUser);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 查询分页
     * @param start
     * @param length
     * @return
     */
    PageInfo<TbUser> page(int start, int length, int draw);

    /**
     * 查询总体数
     * @return
     */
    int count();
}