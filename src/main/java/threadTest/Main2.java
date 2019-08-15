package threadTest;/**
 * Created by lvaolin on 17/9/11.
 */

/**
 * 测试volatile关键字
 *
 * @author lvaolin
 * @create 17/9/11 上午10:30
 */
public class Main2 {


    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true; //主线程修改共享变量   子线程立刻收到修改后的值
    }
}
