package com.guardedsuspension;/**
 * Created by lvaolin on 17/11/1.
 */

/**
 *对话线程
 *
 * @author lvaolin
 * @create 17/11/1 下午2:12
 */
public class TalkThread extends Thread{
    private final RequestQueue input ;
    private final RequestQueue output ;

    public TalkThread(RequestQueue input ,RequestQueue output
    ){
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"begin");
        String inputstr ="你好";
        for (int i = 0; i <20 ; i++) {
            try {
                if(i==0){
                    output.putRequest(inputstr); //获取锁之后才能put   锁是 output
                }
                 inputstr = input.getRequest(); //没有信息时会进入wait状态  锁是input
                //System.out.println(Thread.currentThread().getName()+"gets "+inputstr);
                String outstr = inputstr+"!";
                //System.out.println(Thread.currentThread().getName()+"puts "+outstr);
                output.putRequest(outstr); //获取锁之后才能put   锁是 output
                System.out.println(outstr);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        RequestQueue requestQueue1 = new RequestQueue();
        RequestQueue requestQueue2 = new RequestQueue();
        new TalkThread(requestQueue1,requestQueue2).start();
        new TalkThread(requestQueue2,requestQueue1).start();
    }

}
