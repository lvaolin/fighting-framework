package definelock;/**
 * Created by lvaolin on 17/10/31.
 */

/**
 * 自定义一个锁: 1、可重入锁   2、只有获得过锁的人可以释放锁
 *
 * @author lvaolin
 * @create 17/10/31 下午6:43
 */
public class MutexDemo2 {


    private boolean busy = false;
    private Thread currentThread = null;
    private int  counter ;

    public synchronized void lock(){

        while (busy&&currentThread!=Thread.currentThread()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentThread = Thread.currentThread();
        busy = true;
        counter++;
    }


    public synchronized void unLock(){

        if(busy&&currentThread==Thread.currentThread()&&counter==1){
            counter=0;
            busy = false;
            currentThread = null;
            notifyAll();
        }else if(busy&&currentThread==Thread.currentThread()){
            counter--;
        }


    }

}
