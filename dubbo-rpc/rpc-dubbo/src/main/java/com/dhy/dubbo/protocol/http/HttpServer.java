package com.dhy.dubbo.protocol.http;

import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.start("loalhost",80);
    }

    Tomcat tomcat = new Tomcat();

    public void start(String hostname,Integer port){
        tomcat.setBaseDir(".");
        tomcat.setHostname(hostname);
        tomcat.setPort(port);
        tomcat.getConnector();
        tomcat.addContext("/v1","v1");
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }

    public void stop(){

    }
}
