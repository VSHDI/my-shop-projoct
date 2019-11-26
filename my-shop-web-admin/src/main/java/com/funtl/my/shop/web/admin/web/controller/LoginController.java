package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.comstant.ConstantUtils;
import com.funtl.my.shop.commons.utils.CookieUtils;
import com.funtl.my.shop.domain.User;
import com.funtl.my.shop.web.admin.dao.UserDao;
import com.funtl.my.shop.web.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.PanelUI;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest) {
        /*取Cookie*/
        String userInfo = CookieUtils.getCookieValue(httpServletRequest, "userInfo");

        if (!StringUtils.isBlank(userInfo)){
            String[] split = userInfo.split(":");
            String email = split[0];
            String password = split[1];
            httpServletRequest.setAttribute("email",email);
            httpServletRequest.setAttribute("password",password);
            httpServletRequest.setAttribute("isRemember",true);
        }
        return "login";
    }

    /**
     * 登录逻辑
     *
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email,
                        @RequestParam(required = true) String password,
                        HttpServletRequest httpServletRequest,
                        Model model,
                        HttpServletResponse httpServletResponse
                        ) throws IOException {

        /*User user = userDao.getUser(email, password);

        // 登录失败
        if (user == null) {
            model.addAttribute("message", "用户名或密码错误，请重新输入");
            return login();
        }

        // 登录成功
        else {
            // 将登录信息放入会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, user);
            return "redirect:/main";
        }*/

        User tbUser = userService.login(email, password);
        Boolean isRemember = httpServletRequest.getParameter("isRemember") == null ? false : true;
        System.out.println(isRemember);

//        选择不记住
        if(!isRemember){
            CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,"userInfo");
        }

        // 登录失败
        if (tbUser == null) {
            model.addAttribute("message", "用户名或密码错误，请重新输入");
            return login(httpServletRequest);
        }

        // 登录成功
        else {
            // 将登录信息放入会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);

//            设置Cookie
            if (isRemember){
                CookieUtils.setCookie(httpServletRequest, httpServletResponse,"userInfo",String.format("%s:%s",email,password),7*24*60*60);
            }
            return "redirect:/main";
        }
    }


    /**
     * 注销
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
