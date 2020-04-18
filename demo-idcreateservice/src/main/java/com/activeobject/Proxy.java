package com.activeobject;/**
 * Created by lvaolin on 17/11/23.
 */

/**
 * @author lvaolin
 * @create 17/11/23 下午8:08
 */
public class Proxy  implements ActiveObject{

    private final SchedulerThread scheduler;
    private final Servant servant;

    public Proxy(SchedulerThread scheduler,Servant servant){
        this.scheduler = scheduler;
        this.servant = servant;
    }



    @Override
    public Result<String> makeString(int count, char fillchar) {
        FutureResult<String> future = new FutureResult<String>();
       // scheduler.invoke(new MakeStringRequest(servant,future,count,fillchar));
        return future;
    }

    @Override
    public void displayString(String string) {

    }
}
