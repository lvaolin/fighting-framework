package definelock;/**
 * Created by lvaolin on 17/10/31.
 */

/**
 * 自定义一个锁: 待解决问题   1、不可重入锁   2、任何人可以释放锁
 *
 * @author lvaolin
 * @create 17/10/31 下午6:43
 */
public class MutexDemo {


    private boolean busy = false;


    public synchronized void lock(){

        while (busy){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        busy = true;

    }


    public synchronized void unLock(){

        busy = false;
        notifyAll();

    }

}
