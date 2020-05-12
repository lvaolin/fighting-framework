package com.dhy.cas;


/**
 * 测试 双重检查获取 单例的问题
 * @author lvaolin
 * @create 2019/12/30 9:29 AM
 */
public class DoubleCheck {

    public Object[] arr = new Object[10000];
    {
        for (int j = 0; j <10000 ; j++) {
            for (int i = 0; i < arr.length; i++) {
                arr[i]=i*j;
            }
        }
    }
    private static volatile DoubleCheck instance;
    private DoubleCheck() {}
    public static DoubleCheck getInstance() {
        if (instance==null) {
            synchronized (DoubleCheck.class){
                if(instance==null){
                    instance = new DoubleCheck();
                    System.out.println("我创建了instance");
                }else{
                    System.out.println("别人已经创建成功");
                }
            }
        }else{
            System.out.println("我获取到了instance");
        }
        return instance;
    }


    public static void main(String[] args) {
       // CyclicBarrier cyclicBarrier = new CyclicBarrier(100);
        for (int i = 0; i <100 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        cyclicBarrier.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (BrokenBarrierException e) {
//                        e.printStackTrace();
//                    }
                    DoubleCheck instance = DoubleCheck.getInstance();
                    System.out.println(instance.arr[9999]);
                }
            }).start();
        }
    }

}
