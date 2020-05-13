package com.dhy.tomcat;

/**
 * servlet请求处理基类：Tomcat是满足Servlet规范的容器，所以Tomcat需要提供API：doGet/doPost/service
 */
public abstract class MyServlet {

    public abstract void doGet(MyRequest myRequest,MyResponse myResponse);
    public abstract void doPost(MyRequest myRequest,MyResponse myResponse);
    public void service(MyRequest myRequest,MyResponse myResponse){
        if(myRequest.getMethod().equalsIgnoreCase("POST")){
            doPost(myRequest,myResponse);
        }else if(myRequest.getMethod().equalsIgnoreCase("GET")){
            doGet(myRequest,myResponse);
        }
    }
}
