package com.dhy.dubbo.framework;

public class URL {
    public URL(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public URL() {
    }

    private String host;
    private Integer port;

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
