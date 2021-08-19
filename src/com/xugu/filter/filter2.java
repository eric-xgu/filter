package com.xugu.filter;

//
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebFilter("/s3")
public class filter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init2");
    }

    @Override
    public void destroy() {
        System.out.println("filter destory");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("天穹拦截 ");
        //放行资源
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
