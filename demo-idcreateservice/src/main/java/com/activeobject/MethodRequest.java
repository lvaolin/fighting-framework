package com.activeobject;/**
 * Created by lvaolin on 17/11/23.
 */

/**
 * @author lvaolin
 * @create 17/11/23 下午8:09
 */
public abstract class MethodRequest<T> {

    protected final Servant servant;
    protected final FutureResult<T> future;

    public MethodRequest(Servant servant,FutureResult<T> future){
        this.servant = servant;
        this.future = future;
    }

    public abstract void execute();
}
