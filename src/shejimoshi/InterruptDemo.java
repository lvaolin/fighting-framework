package shejimoshi;/**
 线程中断测试
 * Created by lvaolin on 17/10/30.
 */

import java.util.concurrent.TimeUnit;

/**
 * @author lvaolin
 * @create 17/10/30 下午2:04
 */
public class InterruptDemo {


    public static void main(String[] args) throws InterruptedException {


      Thread thread =   new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println(Thread.currentThread().getName()+"进入睡眠状态");
                    Thread.currentThread().sleep(30000);
                    System.out.println(Thread.currentThread().getName()+"中断睡眠后醒来");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+"收到中断请求");
                    System.out.println("Thread.currentThread().isInterrupted():"+Thread.currentThread().isInterrupted());//返回永远是false，因为中断异常发生后，中断标志会被重置
                }


            }
        });

      thread.start();


        Thread thread2 =   new Thread(new Runnable() {
            @Override
            public void run() {

                while (!Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+"我没有被中断");
                }

                if(Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+"我被中断了");
                }


            }
        });

        thread2.start();




      thread.interrupt();
      thread2.interrupt();
      System.out.println(Thread.currentThread().getName()+"已发出中断请求");
      System.out.println(Thread.currentThread().getName()+"退出");

    }


}
