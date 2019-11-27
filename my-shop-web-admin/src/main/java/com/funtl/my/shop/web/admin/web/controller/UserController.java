package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;
    /*@ModelAttribute
    public TbUser getTbUser(Long id) {
        TbUser tbUser = null;

        *//*id不为空，从数据库获取*//*
        if (id != null) {
            tbUser = tbUserService.getById(id);
            System.out.println("id不为空");
            System.out.println("###############33");
        }

        else {
            System.out.println(tbUser.getId());
            System.out.println(tbUser.getUsername());
            tbUser = new TbUser();
            System.out.println("########################");
        }
        return tbUser;
    }*/


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model){
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

   /* @RequestMapping(value = "form",method = RequestMethod.GET)
    public String from(Model model) {
        TbUser tbUser = new TbUser();
        model.addAttribute("tbUser",tbUser);
        return "user_form";
    }*/

    /**
     * 跳转用户表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbUser tbUser) {
        return "user_form";
    }

    @RequestMapping(value = "{id}/form",method = RequestMethod.GET)
    public String from(@PathVariable Long id,Model model,HttpSession session) {
        TbUser tbUser = tbUserService.getById(id);
        model.addAttribute("tbUser",tbUser);
        session.setAttribute("tbUser",tbUser);

        System.out.println("跳转"+tbUser.getId());
        System.out.println("跳转"+id);
        return "user_form";
    }

    /**
     * 保存用户
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser,
                       RedirectAttributes redirectAttributes,
                       Model model,
                       HttpSession session){
        System.out.println("@@@@@@@@@@@@"+tbUser.getUsername());
        System.out.println("@@@@@@@@@@@@"+tbUser.getUsername());

        BaseResult baseResult = tbUserService.save(tbUser,session);

        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }

        else {
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }

   /* *//**
     * 更新用户
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "{id}/save",method = RequestMethod.POST)
    public String save(@PathVariable Long id,TbUser tbUser,
                       RedirectAttributes redirectAttributes,
                       Model model,HttpSession session){

        BaseResult baseResult = tbUserService.save(tbUser,session);

        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }

        else {
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        BaseResult baseResult = null;
        System.out.println(id);
            tbUserService.deleteById(id);

        return "redirect:/user/list";
    }

    /**
     * 显示用户详情
     *
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return "user_detail";
    }

}
