package com.dhy.concurrency.deadlock;/**
 * Created by lvaolin on 17/10/31.
 */


/**
 * 死锁案例
 *
 * @author lvaolin
 * @create 17/10/31 下午6:00
 */
public class DeadLockDemo {


    public static void main(String[] args) {

        Tool dao = new Dao();
        Tool cha = new Cha();
        new Person("张三",dao,cha).start();
        new Person("李四",cha,dao).start();
    }




    static  class Tool{

    }


    static  class Dao extends Tool{

    }


    static  class Cha extends  Tool{

    }


    static  class Person extends Thread{

        private Tool rightHand;
        private Tool leftHand;
        private String name;

        public Person(String name, Tool dao, Tool cha){
            this.name = name;
            this.rightHand = dao;
            this.leftHand = cha;
        }

        @Override
        public void run() {
            while (true){
                synchronized (rightHand){
                    if(rightHand instanceof Dao){
                        System.out.println(name + "我右手拿到了dao");
                    }else{
                        System.out.println(name + "我右手拿到了cha");
                    }

                    synchronized (leftHand){
                        if(leftHand instanceof Dao){
                            System.out.println(name + "我左手拿到了dao");
                        }else{
                            System.out.println(name + "我左手拿到了cha");
                        }

                        System.out.println(name + "我开始吃饭");

                    }
                }

                System.out.println(name + "我吃饭结束");
            }

        }
    }

}
