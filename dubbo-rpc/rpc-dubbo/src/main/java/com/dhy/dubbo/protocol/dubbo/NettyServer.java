package com.dhy.dubbo.protocol.dubbo;

import com.dhy.dubbo.protocol.http.DispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class NettyServer {

    public static void main(String[] args) {
        NettyServer httpServer = new NettyServer();
        httpServer.start("loalhost", 8080);
    }

    Tomcat tomcat = new Tomcat();
    public void start(String hostname, Integer port) {
        tomcat.setBaseDir(".");
        tomcat.setHostname(hostname);
        tomcat.setPort(port);
        tomcat.getConnector();
        //单独指定某个app的地址，以及上下文路径
        //String contextPath = "/v1";
        //tomcat.addWebapp(contextPath, new File("./webapps/v1").getAbsolutePath());

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());
        tomcat.getHost().addChild(context);
        tomcat.addServlet(contextPath,"dispatchServlet",new DispatcherServlet());
        context.addServletMappingDecoded("/*","dispatchServlet");


        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }

    public void stop() {

    }
}
