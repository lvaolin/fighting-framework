package socket.netty.server;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;
/**
 * @author lvaolin
 * @create 2019/7/25 3:45 PM
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //打印出客户端地址
        System.out.println(ctx.channel().remoteAddress()+", "+msg);
        ctx.channel().writeAndFlush("form server: "+ UUID.randomUUID());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}