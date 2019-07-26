package socket.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


/**
 * @author lvaolin
 * @create 2019/7/25 3:48 PM
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        //服务端的远程地址
        System.out.println(LocalDateTime.now()+"收到了服务器的发票号码报文："+msg+",正在处理中"+Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(LocalDateTime.now()+"处理完毕，给服务器发响应"+Thread.currentThread().getName());
        ctx.writeAndFlush("OKL"+ LocalDateTime.now()+Thread.currentThread().getName());
    }

    /**
     * 当服务器端与客户端进行建立连接的时候会触发，如果没有触发读写操作，则客户端和服务端之间不会进行数据通信，也就是channelRead0不会执行，
     * 当通道连接的时候，触发channelActive方法向服务端发送数据触发服务器端的handler的channelRead0回调，然后
     * 服务端向客户端发送数据触发客户端的channelRead0，依次触发。
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("给我发票号码数据");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}