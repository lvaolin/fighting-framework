package threadTest;/**
 * Created by lvaolin on 17/9/22.
 */

/**
 * join 等待线程完成
 *
 * @author lvaolin
 * @create 17/9/22 下午1:34
 */
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1开始");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1结束");
            }
        });
        thread1.start();
        thread1.join();//等待线程thread1 结束后再继续往下走
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2开始");
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2结束");
            }
        });
        thread2.start();
        thread2.join();//等待线程thread2 结束后再继续往下走
        System.out.println("子线程全部结束。。。");
        System.out.println("主线程结束。。。");

    }
}
