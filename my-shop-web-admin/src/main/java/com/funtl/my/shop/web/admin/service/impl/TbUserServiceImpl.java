package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.utils.RegexpUtils;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

   /* @Override
    public BaseResult save(TbUser tbUser, HttpSession session) {

        BaseResult baseResult = checkTbUser(tbUser);
        TbUser tbUser2 = (TbUser) session.getAttribute("tbUser");
        *//*通过验证*//*
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            tbUser.setUpdated(new Date());
            *//*编辑用户*//*
            if (tbUser.getCreated() != null) {
                System.out.println(tbUser.getId());
                System.out.println(tbUser.getUsername());
                System.out.println(tbUser2.getUsername());
                System.out.println("update");
                tbUser.setId(tbUser2.getId());
                tbUserDao.update(tbUser);
            }
            *//*新增用户*//*
            else {
                tbUser.setCreated(new Date());
                System.out.println(tbUser);
                System.out.println("insert");
                tbUserDao.insert(tbUser);
            }
            baseResult.setMessage("保存用户信息成功");
        }
        return baseResult;
    }*/
    @Override
    public BaseResult save(TbUser tbUser, HttpSession session) {

        BaseResult baseResult = checkTbUser(tbUser);
        TbUser tbUser2 = (TbUser) session.getAttribute("tbUser");
        /*通过验证*/
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            tbUser.setUpdated(new Date());
         /*   *//*编辑用户*//*

                System.out.println(tbUser.getId());
                System.out.println(tbUser.getUsername());
                System.out.println(tbUser2.getUsername());
                System.out.println("update");
                tbUser.setId(tbUser2.getId());
                tbUserDao.update(tbUser);*/

            /*新增用户*/

                tbUser.setCreated(new Date());
                System.out.println(tbUser);
                System.out.println("insert");
                tbUserDao.insert(tbUser);

            baseResult.setMessage("保存用户信息成功");
        }
        return baseResult;
    }
    @Override
    public BaseResult update(TbUser tbUser, HttpSession session) {

        BaseResult baseResult = checkTbUser(tbUser);
        TbUser tbUser2 = (TbUser) session.getAttribute("tbUser");
        /*通过验证*/
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            tbUser.setUpdated(new Date());
          //*编辑用户*//*

                System.out.println(tbUser.getId());
                System.out.println(tbUser.getUsername());
                System.out.println(tbUser2.getUsername());
                System.out.println("update");
                tbUser.setId(tbUser2.getId());
                tbUserDao.update(tbUser);

            baseResult.setMessage("保存用户信息成功");
        }
        return baseResult;
    }



    @Override
    public void deleteById(Long id) {
        tbUserDao.deleteById(id);
    }

    @Override
    public void delete(TbUser tbUser) {
        tbUserDao.delete(tbUser);
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }



    @Override
    public List<TbUser> selectByName(String username) {
        return tbUserDao.selectByName(username);
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.selectByEmail(email);

        if (tbUser != null) {
            if (password.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return null;
    }

    /**
     * 用户信息验证
     * @param tbUser
     * @return
     */
    private BaseResult checkTbUser(TbUser tbUser) {
        BaseResult baseResult = BaseResult.success();

        /*非空验证*/
        if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.fail("姓名不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = BaseResult.fail("密码不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.fail("电话不能为空，请重新输入");
        }
        else if (!RegexpUtils.checkPhone(tbUser.getPhone())) {
            baseResult = BaseResult.fail("电话格式不对，请重新输入");
        }
        else if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱不能为空，请重新输入");
        }
        else if (!RegexpUtils.checkEmail(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱格式不对，请重新输入");
        }

        return baseResult;
    }

}