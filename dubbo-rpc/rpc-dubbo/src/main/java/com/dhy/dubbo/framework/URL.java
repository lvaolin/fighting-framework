package com.dhy.dubbo.framework;

public class URL {
    public URL(String applicationName,String host, Integer port) {
        this.applicationName = applicationName;
        this.host = host;
        this.port = port;
    }

    public URL() {
    }
    private String applicationName;
    private String host;
    private Integer port;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }



    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


}
