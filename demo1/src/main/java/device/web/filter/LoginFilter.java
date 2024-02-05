package cn.yzw.device.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginFilter implements Filter {

    private List<String> excluded = new ArrayList<>();

    public void destroy() {
        System.out.println(this.getClass() + " destroy ...." + new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(new Date()));
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;

        HttpServletResponse response = (HttpServletResponse) resp;


        Object user = request.getSession().getAttribute("USER");

        if (canIgnore(request)) {

            chain.doFilter(req,resp);

        }else {
            if (user == null){
                response.sendRedirect(request.getContextPath() + "/page/login.jsp");
            }else {
                chain.doFilter(req, resp);
            }

        }


    }

    public void init(FilterConfig config) throws ServletException {

        System.out.println(this.getClass() + " init ...." + new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(new Date()));

        String ignores = config.getInitParameter("ignores");
        String contextPath = config.getServletContext().getContextPath();
        String[] split = ignores.split(",");
        for (String s : split) {
            excluded.add(contextPath + s);
        }
    }

    private boolean canIgnore(HttpServletRequest request){

        String requestURI = request.getRequestURI();
        for (String s : excluded) {
            if (requestURI.startsWith(s)){
                return true;
            }
        }
        return false;
    }

}
