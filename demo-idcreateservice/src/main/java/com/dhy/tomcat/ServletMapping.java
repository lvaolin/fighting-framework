package com.dhy.tomcat;

/**
 * Servlet配置：对比之前在web开发中，会在web.xml中通过和指定哪个URL交给哪个servlet来处理
 */
public class ServletMapping {
    private String servletName;
    private String url;
    private String clazz;

    public ServletMapping(String servletName, String url, String clazz){
        this.servletName=servletName;
        this.url=url;
        this.clazz=clazz;
    }

    public String getServletName() {
        return servletName;
    }
    public void setServletName(String servletName) {
        this.servletName = servletName;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getClazz() {
        return clazz;
    }
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
