package com.xiepanpan.web;

import com.xiepanpan.constant.CommonConstant;
import com.xiepanpan.entity.User;
import com.xiepanpan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * describe:
 *
 * @author xiepanpan
 * @date 2018/10/26
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String toke = (String) request.getSession().getAttribute("toke");
        if (!StringUtils.isEmpty(toke)){
            response.sendRedirect(request.getContextPath()+"/quartz/listJob");
        }
        return "user/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username")String username,@RequestParam("password")String password,
                        HttpServletRequest request,HttpServletResponse response) throws IOException {
        User u = new User(username,password);
        User user = userService.getUser(u);
        if (user!=null) {
            HttpSession session = request.getSession();
            session.setAttribute(CommonConstant.LONGIN_TOKEN,UUID.randomUUID().toString().replace("-",""));
            session.setAttribute("username",username);
            response.sendRedirect(request.getContextPath()+"/quartz/listJob");
        }else {
            request.setAttribute("message","用户名或密码错误");
        }
        return "user/login";
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(CommonConstant.LONGIN_TOKEN);
        return "redirect:/user/toLogin";
    }
}
