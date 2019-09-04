package threadTest.shiti;/**
 * Created by lvaolin on 17/10/11.
 */


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 有个boss在做年度计划，然后他要求三名员工做完各自的报表交给他后，他才能继续做年度计划。用Java多线程模拟实现。
 *
 * @author lvaolin
 * @create 17/10/11 上午10:25
 */
public class ShiTi4 {


    volatile static   CountDownLatch countDownLatch = new CountDownLatch(3);
    //当call=true时表示收到了老板的任务通知
    volatile static   boolean call = false;
    public static void main(String[] args) throws InterruptedException {

        //A员工线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!call){
                    try {
                        System.out.println("A员工准备就绪等待老板任务");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A员工开始工作并提交了报表");
                countDownLatch.countDown();
            }
        },"employee_a").start();

        //B员工线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!call){
                    try {
                        System.out.println("B员工准备就绪等待老板任务");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("B员工开始工作并提交了报表");
                countDownLatch.countDown();
            }
        },"employee_b").start();

        //C员工线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!call){
                    try {
                        System.out.println("C员工准备就绪等待老板任务");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("C员工开始工作并提交了报表");
                countDownLatch.countDown();
            }
        },"employee_c").start();


        //等待老板分配任务
        TimeUnit.SECONDS.sleep(5);


        //老板线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是老板我正在做年度计划，需要ABC三名员工提交报表才能继续，通知他们");
                call = true;
                System.out.println("已经通知完毕，等待3名员工全部提交完成");
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("员工报表终于都提交了，我该继续做年度计划了");
            }
        },"boss").start();





    }


}
