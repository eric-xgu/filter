package com.xugu.monitor;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/*session对象创建 人数+1 销毁-1*/
@WebListener
public class OnListener implements HttpSessionListener {
    private int num;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        num++;
        se.getSession().getServletContext().setAttribute("onlinenum",num);
        System.out.println("online:"+se.getSession().getServletContext().getAttribute("onlinenum"));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        num--;
        se.getSession().getServletContext().setAttribute("onlinenum",num);
        System.out.println("online:"+se.getSession().getServletContext().getAttribute("onlinenum"));
    }
}
