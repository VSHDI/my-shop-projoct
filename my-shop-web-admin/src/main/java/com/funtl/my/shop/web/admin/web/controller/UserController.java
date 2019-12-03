package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("这是热部署吗？");
        System.out.println("我确定这是热部署");
        System.out.println("我不确定是否够快，或者说cpu不会爆开");
        System.out.println("快吗？");
        System.out.println("还可以");
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

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(TbUser tbUser,Model model) {
        List<TbUser> tbUsers = tbUserService.search(tbUser);
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }

    @ResponseBody
    @RequestMapping(value = "delete" , method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNoneBlank(ids)) {
            String[] idArray = ids.split(",");
            tbUserService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除用户成功");
        }
        else {
            baseResult = BaseResult.fail("删除用户失败");
        }
        return baseResult;
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request) {
      String strDraw = request.getParameter("draw");
      String strStart = request.getParameter("start");
      String strLength = request.getParameter("length");

     int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
     int start = strStart == null ? 0 : Integer.parseInt(strStart);
     int length = strLength == null ? 0 : Integer.parseInt(strLength);

    //封装Datatables需要的结果
     PageInfo<TbUser> pageInfo = tbUserService.page(start,length,draw);

        return pageInfo;
    }


}
