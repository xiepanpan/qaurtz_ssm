//package com.xiepanpan.util;
//
//import com.xiepanpan.constant.CommonConstant;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * @program: quartz_ssm
// * @description: 未登录进行拦截
// * @author: xiepanpan
// * @create: 2018-10-29 20:45
// **/
//public class UserInterceptor implements HandlerInterceptor{
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        HttpSession session = httpServletRequest.getSession();
//        String token = (String) session.getAttribute(CommonConstant.LONGIN_TOKEN);
//        //未登录
//        if (StringUtils.isEmpty(token)) {
//            String localUrl = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + "/";
//            httpServletResponse.sendRedirect(localUrl+"user/toLogin");
//            return false;
//        }else {
//            return true;
//        }
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//}
