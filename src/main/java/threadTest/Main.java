package threadTest;/**
 * Created by lvaolin on 17/9/11.
 * 关于主线程子线程共享变量修改的测试
 */

/**
 * @author lvaolin
 * @create 17/9/11 上午10:09
 */
public class Main {

    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Long start = System.currentTimeMillis();
                while (flag){
                    System.out.println("我1在运行呢。。。");

                }
                Long end = System.currentTimeMillis();
                System.out.println((end-start)/1000);
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                Long start = System.currentTimeMillis();
                while (flag){
                    System.out.println("我2在运行呢。。。");
                    flag = false;
                }
                Long end = System.currentTimeMillis();
                System.out.println((end-start)/1000);
            }
        }).start();

       // Thread.sleep(3000);
       // flag = false;
    }
}
