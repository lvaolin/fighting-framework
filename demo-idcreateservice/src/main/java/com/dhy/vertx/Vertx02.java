package com.dhy.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class Vertx02 {

    public static void main(String[] args) {
        // 从工厂中获取vertx实例
        Vertx vertx = Vertx.vertx();
        // 创建httpServer
        HttpServer httpServer = vertx.createHttpServer();
        // 创建web路由器
        Router router = Router.router(vertx);

        //////////////////////////////////   同步处理的请求   ////////////////////////////////////////
        // 针对/sync路径下的所有类型请求
        router.route("/sync").order(1).handler(context -> {
            HttpServerRequest request = context.request();
            String name = request.getHeader("name");
            context.response().end("good morning " + name);
        });
        // 针对/sync/api/*路径下的所有post请求
        router.route().handler(BodyHandler.create());

        router.post("/sync/api/*").order(-1).handler(context -> {
            HttpServerRequest request = context.request();
            String gender = request.getParam("gender");
           // String formAttribute = request.getFormAttribute();
           // String header = request.getHeader();

           context.response().end("sync线程: " + Thread.currentThread().getName() + "\napi: " + gender);
        });
        //////////////////////////////////   异步处理的请求   ////////////////////////////////////////
        router.get("/async").blockingHandler(context -> {
            // context.request();
            /**
             * 异步处理请求
             * 适合执行耗时操作:
             * 1.数据库访问
             * 2.服务访问
             *
             */
            System.out.println("收到请求-----"+context.request().getParam("name"));
            context.response().end("async线程: hello " + context.request().getParam("name"));
        });

        httpServer.requestHandler(router::accept);
        // 监听端口
        httpServer.listen(80);
    }
}