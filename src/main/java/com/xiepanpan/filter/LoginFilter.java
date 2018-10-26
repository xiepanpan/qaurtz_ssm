package com.xiepanpan.filter;

import com.xiepanpan.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * describe:过滤器 跳转到登录页面
 *
 * @author xiepanpan
 * @date 2018/10/26
 */
public class LoginFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init LoginFilter ...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute(CommonConstant.LONGIN_TOKEN);
        if (!StringUtils.isEmpty(token)) {
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect(request.getContextPath()+"/user/toLogin");
        }
    }

    @Override
    public void destroy() {
        log.info("destroy LoginFilter ...");
    }
}
