package com.dhy.servelt4;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "hello",urlPatterns = "/hello",initParams = {
        @WebInitParam(name = "charSet",value = "utf-8",description = "编码规则")
})
public class HelloController extends HttpServlet {
    private String charSet;
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.charSet = config.getInitParameter("charSet");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        System.out.println(this);
        //映射信息
        System.out.println(req.getHttpServletMapping().getPattern());
        System.out.println(req.getHttpServletMapping().getMatchValue());
        System.out.println(req.getHttpServletMapping().getServletName());
        System.out.println(charSet);
        //服务器端推送
       // req.newPushBuilder().addHeader("key","test").push();

        resp.getWriter().println("hello,"+name);
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        resp.getWriter().println("hello");
        resp.getWriter().close();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
