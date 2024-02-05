package cn.yzw.device.web.filter;

import cn.yzw.device.vo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object user = request.getSession().getAttribute("USER");
        System.out.println("roleFilter.....");
        if (user != null){
            User _user = (User) user;
            if (((User) user).getRoleId() == 2) filterChain.doFilter(request,response);
        }
         response.sendRedirect(request.getContextPath()+"/error/403.jsp");

    }

    @Override
    public void destroy() {

    }
}
