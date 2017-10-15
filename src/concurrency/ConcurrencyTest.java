package concurrency;/**
 * Created by lvaolin on 17/9/12.
 */

import javax.swing.plaf.synth.SynthEditorPaneUI;

/**
 * @author lvaolin
 * @create 17/9/12 下午6:23
 */
public class ConcurrencyTest {

private  static final long count=2100000000l;
    public static void main(String[] args) throws InterruptedException {

                concurrency();
                serial();

    }

    private static  void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long a = 0;
                for (long i = 0; i <count ; i++) {
                    a+=5;
                }
            }
        });
        thread.start();
        long b = 0;
        for (int i = 0; i <count ; i++) {
            b--;
        }
        thread.join(); //等待thread线程执行完毕才能往下执行
        long time = System.currentTimeMillis()-start;
        System.out.println("执行次数："+count+" 2线程并发执行时间："+time+"ms,b="+b);
    }



    private static void serial(){
        long start = System.currentTimeMillis();

        long a = 0;
        for (long i = 0; i <count ; i++) {
            a+=5;
        }


        long b = 0;
        for (long i = 0; i <count ; i++) {
            b--;
        }


        long time = System.currentTimeMillis()-start;
        System.out.println("执行次数："+count+" 单线程执行时间："+time+"ms,b="+b+"，a="+a);
    }

}
