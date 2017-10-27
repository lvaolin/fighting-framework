package base;/**
 * Created by lvaolin on 17/10/17.
 */

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.TimeUnit;

/**
 * @author lvaolin
 * @create 17/10/17 上午8:56
 */
public class BaseTest {
    static volatile   Boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (flag){
                        System.out.println(System.currentTimeMillis());
                    }
                }
            }).start();
        }


        TimeUnit.SECONDS.sleep(3);
        flag = false;

    }

}
