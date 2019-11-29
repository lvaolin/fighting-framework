package socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Java NIO 测试  服务器端
 *
 * @author lvaolin
 * @create 2019/11/11 8:16 PM
 */
public class NioTestServer {

    public static void main(String[] args) throws IOException {
        //打开一个服务器端监听通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(88));
        //通道非堵塞模式
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }
}
