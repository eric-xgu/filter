package com.xugu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String met=request.getMethod();
        if(met.equals("POST")){
            request.setCharacterEncoding("UTF-8");
            filterChain.doFilter(request,response);
        }
        if(met.equals("GET")){
            String sv=request.getServletContext().getServerInfo();
            String version=sv.substring(sv.indexOf("/")+1,sv.indexOf("."));
            if(Integer.parseInt(version)<8){
                HttpServletRequest request1=new Mywapper(request);
                filterChain.doFilter(request1,response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
//内部类，重写HttpServletRequestWrapper包装类对象，重写getParameter
class Mywapper extends HttpServletRequestWrapper {
    private HttpServletRequest request;

    public Mywapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    public String getParameter(String name) {
        String value=request.getParameter(name);
        try{
        if(value!=null&&"".equals(value.trim())){
            value=new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
}
