package com.xugu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//1、拦截所有请求
//2、放行对应的资源

@WebFilter("/*")
public class filter3 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init3");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //放行首页面,静态资源js，css

        //未登陆的请求拦截
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String uri=request.getRequestURI();
        System.out.println(uri);
        //1、指定页面放行
        if(uri.contains("/login.jsp")||uri.contains("/index.jsp")){
            System.out.println("1");
            filterChain.doFilter(request,response);
            return;
        }
        //2、静态资源放行
        if(uri.contains(".css")||uri.contains(".xml")){
            System.out.println("2");
            filterChain.doFilter(request,response);
            return;
        }
        //3、指定地址放行
        if(uri.contains("/login")||uri.contains("/index")){
                System.out.println("3");
                filterChain.doFilter(request, response);
                return;
        }
        //4、登陆后放行
        String name=(String)request.getSession().getAttribute("user");
        if(name!=null&&!"".equals(name)){
            System.out.println("4");
            ServletContext sv=request.getSession().getServletContext();
            HttpSession session=request.getSession();
            int num=(Integer) sv.getAttribute("onlinenum");
            session.setAttribute("num",num);
            filterChain.doFilter(request,response);
            return;
        }


        response.sendRedirect("login.jsp");
    }

    @Override
    public void destroy() {

    }
}
