package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;
    @ModelAttribute
    public TbUser getTbUser(Long id) {
        TbUser tbUser = null;

        /*id不为空，从数据库获取*/
        if (id != null) {
            tbUser = tbUserService.getById(id);
        }

        else {
            tbUser = new TbUser();
        }
        return tbUser;
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model){
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String from(Model model) {
        TbUser tbUser = new TbUser();
        model.addAttribute("tbUser",tbUser);
        return "user_form";
    }

    /**
     * 保存用户
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes, Model model){
        BaseResult baseResult = tbUserService.save(tbUser);

        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }

        else {
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }



}
