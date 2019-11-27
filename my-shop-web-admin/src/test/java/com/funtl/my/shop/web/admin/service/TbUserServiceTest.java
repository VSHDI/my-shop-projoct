package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserDao tbUserDao;

    @Test
    public void testSelectAll() {
        List<TbUser> tbUsers = tbUserDao.selectAll();
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setEmail("admin@adm2i1n.com");
        tbUser.setPassword("adm31in");
        tbUser.setPhone("1588888w88888");
        tbUser.setUsername("Lusifer11w1");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserDao.insert(tbUser);
    }

    @Test
    public void testDeleteById() {
        Long id = 37L;
        tbUserDao.deleteById(id);
    }

    @Test
    public void testDelete() {
        TbUser tbUser = new TbUser();
        tbUser.setId(36L);

        tbUserDao.delete(tbUser);
    }

    @Test
    public void testGetById() {
        TbUser tbUser = tbUserDao.getById(35L);
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void testUpdate() {
        TbUser tbUser = tbUserDao.getById(35L);
        tbUser.setUpdated(new Date());
        tbUser.setUsername("1232333");

        tbUserDao.update(tbUser);
    }

    @Test
    public void testSelectByName() {
        List<TbUser> tbUsers = tbUserDao.selectByName("uni");
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }
}