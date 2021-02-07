package com.dhy.vertx;


import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;

import static io.vertx.core.json.Json.encode;

public class Vertx01 {

    /**
     * Vert.x Web Client（Web客户端）是一个异步的 HTTP 和 HTTP/2 客户端。
     *
     * Web Client使得发送 HTTP 请求以及从 Web 服务器接收 HTTP 响应变得更加便捷，同时提供了额外的高级功能，例如：
     *     JSON体的编码和解码
     *     请求和响应泵
     *     请求参数的处理
     *     统一的错误处理
     *     提交表单
     *
     * 制作Web Client的目的并非为了替换Vert.x Core中的 HttpClient，
     * 而是基于该客户端，扩展并保留其便利的设置和特性，
     * 例如请求连接池（Pooling），HTTP/2的支持，流水线／管线的支持等。
     * 当您需要对 HTTP 请求和响应做细微粒度控制时，您应当使用 HttpClient。
     * 另外Web Client并未提供 WebSocket API，此时您应当使用 HttpClient
     *
     * @param args
     */

    public static void main(String[] args) {
        WebClient webClient = WebClient.create(Vertx.vertx());
        getValueForCompany(webClient,"dahuangya",2);
        System.out.println("------");
        webClient.close();
    }

    /**
     * 异步返回结果
     * @param client
     * @param company
     * @param numberOfShares
     * @return
     */
    private static void getValueForCompany(WebClient client, String company, int numberOfShares) {
        // 创建预期对象，它将要在收到估值的时候得到赋值
        client.get("/async?name=" + encode(company))
                .as(BodyCodec.string())
                .send(ar -> {
                    if (ar.succeeded()) {
                        HttpResponse<String> response = ar.result();
                        if (response.statusCode() == 200) {
                            System.out.println("200 okl");
                            System.out.println(response.body());
                        } else {
                            System.out.println("no----");
                        }
                    } else {
                        System.out.println(ar.cause());
                    }
                });

    }
}