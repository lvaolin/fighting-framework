package com.activeobject;/**
 * Created by lvaolin on 17/11/23.
 */

/**
 * @author lvaolin
 * @create 17/11/23 下午8:09
 */
public class ActivationQueue {

    public synchronized void putRequest(MethodRequest request){
         //入队
    }

    public synchronized MethodRequest takeRequest(){
        //出队
        return null;
    }

}
