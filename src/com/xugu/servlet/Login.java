package com.xugu.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login",value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // req.setCharacterEncoding("UTF-8");
        //new String(req.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
        String username=req.getParameter("username");
        String passwd=req.getParameter("passwd");
        System.out.println(username+":"+passwd);
        if(username==null||"".equals(username)) {
            System.out.println("sendR");
            resp.sendRedirect("login.jsp");
            return;
        }
        ServletContext sv=req.getSession().getServletContext();
        HttpSession session=req.getSession();
        int num=(Integer) sv.getAttribute("onlinenum");
        session.setAttribute("num",num);
        System.out.println("login:"+username);
        session.setAttribute("user",username);
        System.out.println("sendR1");
        resp.sendRedirect("home.jsp");
        return;
    }
}
