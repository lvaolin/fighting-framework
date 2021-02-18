package com.dhy.ratelimit.resilience4j;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Project dhy-springboot-resilience4j
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/1/29 2:34 下午
 */
public class LoginService implements ILoginService{

    public boolean login(){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println( " login pass");
        return true;
    }

    public String hello(String name){
        return "hello "+ name;
    }
}
