package threadTest.lock.readwritelock;/**
 * Created by lvaolin on 17/11/3.
 */

/**
 * 测试类
 *
 * @author lvaolin
 * @create 17/11/3 上午10:40
 */
public class Main {


    public static void main(String[] args) {
        Data data = new Data();
        for (int i = 0; i <10 ; i++) {
            new ReadThread("读线程"+i,data).start();
        }

        new WriteThread("写线程",data).start();
    }
}
