package threadTest;/**
 * Created by lvaolin on 17/9/22.
 */

/**
 * 线程的暂停恢复终止
 *
 * @author lvaolin
 * @create 17/9/22 上午9:29
 */
public class Deprecated {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runner(),"Thread Runner" );
        thread.start();
        Thread.sleep(3000);
        System.out.println("Thread Runner status:"+thread.getState());
        thread.suspend();
        Thread.sleep(20000);
        System.out.println("Thread Runner status:"+thread.getState());
        thread.resume();
        Thread.sleep(2000);
        System.out.println("Thread Runner status:"+thread.getState());
        thread.stop();
        Thread.sleep(2000);
        System.out.println("Thread Runner status:"+thread.getState());

    }



    static class Runner implements Runnable{
        @Override
        public void run() {
            while (true){
                System.out.println(System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
