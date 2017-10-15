package threadTest;/**
 * Created by lvaolin on 17/9/27.
 */

import java.util.concurrent.TimeUnit;

/**
 * 多线程之间的 等待/通知机制
 *
 * @author lvaolin
 * @create 17/9/27 上午9:20
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();
    public static void main(String[] args) {
          new Thread(new Wait()).start();

          new Thread(new Notify()).start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main1:"+System.currentTimeMillis());
    }


    static class Wait implements  Runnable{

        @Override
        public void run() {
            synchronized (lock){

                while (flag){
                    try {
                        System.out.println("wait1:"+System.currentTimeMillis());
                        lock.wait();
                        System.out.println("wait2:"+System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("wait3:"+System.currentTimeMillis());
            }
        }
    }


    static class Notify implements  Runnable{

        @Override
        public void run() {

            synchronized (lock){
                System.out.println("Notify1:"+System.currentTimeMillis());
                lock.notify();
                flag = false;
                System.out.println("Notify2:"+System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock){
                System.out.println("Notify3:"+System.currentTimeMillis());
                System.out.println("Notify4:"+System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}

/**
 * 输出顺序如下：
 * wait1:1506478063560
 Notify1:1506478063562
 Notify2:1506478063562
 wait2:1506478065567
 wait3:1506478065567
 Notify3:1506478066571
 Notify4:1506478066571
 main1:1506478068563

 */