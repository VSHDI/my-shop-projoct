package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao {
    /**
     * 查询用户表全部信息
     * @return
     */
    List<TbUser> selectAll();

    void insert(TbUser tbUser);

    void deleteById(Long id);

    void delete(TbUser tbUser);

    TbUser getById(Long id);

    void update(TbUser tbUser);

    List<TbUser> selectByName(String username);

    TbUser selectByEmail(String email);

    /**
     * 搜索
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
     * 分页查询
     * @param params
     * @return
     */
    List<TbUser> page(Map<String,Object> params);

    /**
     * 查询总体数
     * @return
     */
    int count();
}
