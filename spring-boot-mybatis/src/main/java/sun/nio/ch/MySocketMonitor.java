package sun.nio.ch;

import oracle.net.nt.TcpNTAdapter;

import java.net.Socket;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Project spring-boot-mybatis
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/12/2 下午4:13
 */
public class MySocketMonitor {
    public static void print() {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printSocket();
                printOracleTcpNTAdapter();
            }
        }).start();
    }

    private static void printSocket() {
        try {

            Map<Socket, Exception> map = (Map<Socket, Exception>) Socket.class.getDeclaredField("myMap").get(null);
            System.out.println("遗留Socket连接：" + map.size() + "个");
            map.forEach((s, e) -> {
                System.out.println(s.toString() + "---Socket->");
                System.out.println(e.getMessage() + "---Socket->");
                e.printStackTrace();
            });
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private static void printOracleTcpNTAdapter() {
        try {

            Map<TcpNTAdapter, Exception> map = (Map<TcpNTAdapter, Exception>) TcpNTAdapter.class.getDeclaredField("myMap").get(null);
            System.out.println("遗留TcpNTAdapter连接：" + map.size() + "个");
            map.forEach((s, e) -> {
                System.out.println(s.toString() + "---TcpNTAdapter->");
                System.out.println(e.getMessage() + "---TcpNTAdapter->");
                e.printStackTrace();
            });
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

//    private static void printSocketAdaptor(){
//        try {
//            Map<SocketAdaptor,Exception> map= (Map<SocketAdaptor, Exception>)SocketAdaptor.class.getDeclaredField("myMap").get(null);
//            System.out.println("遗留SocketAdaptor连接："+map.size()+"个");
//            map.forEach((s,e)->{
//                System.out.println(s.toString()+"---SocketAdaptor->");
//                System.out.println(e.getMessage() + "---SocketAdaptor->");
//                e.printStackTrace();
//            });
//        } catch (Exception e) {
//            //e.printStackTrace();
//        }
//    }
    //SocketChannelImpl 为私有类private，无法访问，所以这个方案就不可行了
//    public static void printSocketChannel(){
//        try {
////                    TimeUnit.SECONDS.sleep(20);
////                    Map<SocketChannelImpl,Exception> map= (Map<SocketChannelImpl, Exception>)SocketChannelImpl.class.getDeclaredField("myMap").get(null);
////                    System.out.println("遗留SocketChannel连接："+map.size()+"个");
////                    map.forEach((s,e)->{
////                        System.out.println(s.toString()+"---SocketChannel->");
////                        System.out.println(e.getMessage() + "---SocketChannel->");
////                        e.printStackTrace();
////                    });
////                } catch (Exception e) {
////                    //e.printStackTrace();
////                }
//    }
}
