package com.socket.netty.server;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lvaolin
 * @create 2019/7/25 3:45 PM
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(LocalDateTime.now()+"收到了客户端的报文："+msg+Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(LocalDateTime.now()+"处理完毕，响应客户端"+Thread.currentThread().getName());
        ctx.channel().writeAndFlush(LocalDateTime.now()+"发票号码："+ UUID.randomUUID()+Thread.currentThread().getName());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}