package lagou;/**
 * Created by lvaolin on 17/10/27.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvaolin
 * @create 17/10/27 下午3:57
 */
public class Test {

    private static List buf = new ArrayList();

    static  int MAX_SIZE=10;
    static  int MIN_SIZE=0;
    public  synchronized void put(Object o){

        while (buf.size()>=MAX_SIZE){
            try {
                System.out.println(Thread.currentThread().getName()+"容量满，生产进入等待"+buf.size());
                wait();
                System.out.println(Thread.currentThread().getName()+"被唤醒，进入生产"+buf.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        buf.add(o);
        System.out.println(Thread.currentThread().getName()+"生产一个"+buf.size());
        notify();
        System.out.println(Thread.currentThread().getName()+"通知可以消费了"+buf.size());

    }


    public synchronized Object get(){

        while (buf.size()<=MIN_SIZE){
            try {
                System.out.println(Thread.currentThread().getName()+"容量空，消费进入等待"+buf.size());
                wait();
                System.out.println(Thread.currentThread().getName()+"被唤醒，进入消费"+buf.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Object o = buf.remove(0);
        System.out.println(Thread.currentThread().getName()+"消费一个"+buf.size());
        notify();
        System.out.println(Thread.currentThread().getName()+"通知可以生产了"+buf.size());
        return o;
    }


    public static void main(String[] args) {
        Test test = new Test();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    test.get();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    test.get();
                }

            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    test.put(new Object());
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    test.put(new Object());
                }

            }
        }).start();
    }
}
