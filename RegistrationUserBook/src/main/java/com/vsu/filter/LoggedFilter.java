package com.vsu.filter;

import com.vsu.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/", "/login", "/user/*", "/book/*"})
public class LoggedFilter implements Filter {

    public static final String LOGIN = "/login";
    public static final String USER_home = "/user/home";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String contextPath = httpServletRequest.getContextPath();
        String loginURI = httpServletRequest.getContextPath() + LOGIN;
        boolean isLoginRequest = httpServletRequest.getRequestURI().equals(loginURI);
        User user = (User) session.getAttribute("user");
        if (!isLoginRequest && user == null) {
            httpServletResponse.sendRedirect(contextPath + LOGIN);
        } else if (isLoginRequest && user != null) {
            httpServletResponse.sendRedirect(contextPath + USER_home);
        } else {
            chain.doFilter(request, response);
        }
    }
}
