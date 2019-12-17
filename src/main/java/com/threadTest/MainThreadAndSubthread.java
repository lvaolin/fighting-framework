package com.threadTest;/**
 * Created by lvaolin on 17/9/14.
 */

/**
 * 关于main线程和子线程之间关系的测试
 *
 * @author lvaolin
 * @create 17/9/14 上午8:49
 */
public class MainThreadAndSubthread {


    //1、Main线程是个非守护线程，不能设置成守护线程
  /* public static void main(String[] args) {
                System.out.println(" parent thread begin ");
                Thread.currentThread().setDaemon(true); //设置为守护线程会报错
   }*/


    //2、Main线程结束，其他线程一样可以正常运行，主线程，只是个普通的非守护线程，用来启动应用程序，不能设置成守护线程；除此之外，它跟其他非守护线程没有什么不同。主线程执行结束，其他线程一样可以正常执行。
    //这样其实是很合理的，按照操作系统的理论，进程是资源分配的基本单位，线程是CPU调度的基本单位。对于CPU来说，其实并不存在java的主线程和子线程之分，都只是个普通的线程。
    // 进程的资源是线程共享的，只要进程还在，线程就可以正常执行，换句话说线程是强依赖于进程的。也就是说，线程其实并不存在互相依赖的关系，一个线程的死亡从理论上来说，不会对其他线程有什么影响。
      /* public static void main(String[] args) {
                 System.out.println("parent thread begin ");


                 new Thread(new Runnable() {
                     @Override
                     public void run() {
                         System.out.println("子线程1开始");
                         try {
                             Thread.sleep(2000);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                         System.out.println("子线程1退出");
                     }
                 }).start();

                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           System.out.println("子线程2开始");
                           try {
                               Thread.sleep(2000);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                           System.out.println("子线程2退出");
                       }
                   }).start();

                 System.out.println("parent thread over ");
       }*/


    //3 Main线程结束。其它线程也可以立即退出，当且仅当这些子线程全部为守护线程

    // java虚拟机(相当于进程)退出的时机是：虚拟机中所有存活的线程都是守护线程。只要还有存活的非守护线程虚拟机就不会退出，而是等待非守护线程执行完毕；
    // 反之，如果虚拟机中的线程都是守护线程，那么不管这些线程的死活java虚拟机都会退出。

   /* public static void main(String[] args) {


        MyThread myThread = new MyThread();
        myThread.setDaemon(true);
        myThread.start();

        MyThread2 myThread2 = new MyThread2();
        myThread2.setDaemon(true);
        myThread2.start();

        System.out.println("Main线程退出");//主线程退出

    }*/


   // (四)Main线程结束，只要有一个非守护子线程还在运行，其它守护子线程就会一直运行，直到没有了非守护线程。

    public static void main(String[] args) {


        MyThread myThread = new MyThread();
        myThread.setDaemon(true);
        myThread.start();

        MyThread2 myThread2 = new MyThread2();
        myThread2.start();

        System.out.println("Main线程退出");//主线程退出

    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("mythread 是守护线程  开始运行");
            Thread.sleep(5000);//sleep期间Main线程已经退出
            System.out.println("mythread 结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class MyThread2 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("mythread2 是非守护线程  开始运行");
            Thread.sleep(10000);//sleep期间Main线程已经退出
            System.out.println("mythread2  结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}