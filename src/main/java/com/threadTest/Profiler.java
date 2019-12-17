package com.threadTest;/**
 * Created by lvaolin on 17/9/27.
 */


import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal的使用
 *
 * @author lvaolin
 * @create 17/9/27 下午8:52
 */
public class Profiler {


    private  static final ThreadLocal<Long> threadLocal = new ThreadLocal<Long>(){

        protected Long initialValue(){
            return System.currentTimeMillis();
        }

    };

    static final void  begin(){
        threadLocal.set(System.currentTimeMillis());
    }

    static final void end(){
        System.out.println(System.currentTimeMillis()-threadLocal.get());
    }


    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(3);
        Profiler.end();
    }

}
