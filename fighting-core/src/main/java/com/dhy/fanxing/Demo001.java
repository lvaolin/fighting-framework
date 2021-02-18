package com.dhy.fanxing;/**
 * Created by lvaolin on 17/11/9.
 */

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 泛型使用测试
 *
 * @author lvaolin
 * @create 17/11/9 下午7:25
 */
public class Demo001 {


    public static void main(String[] args) {
         a();
         b();
    }


    static void  a(){
        ArrayList<String> a = new ArrayList<String>();
        ArrayList b = new ArrayList();
        Class c1 = a.getClass();
        Class c2 = b.getClass();
        System.out.println(c1 == c2); //true
        System.out.println(c1.getName()); //true
    }

    static void b(){
        ArrayList<String> a = new ArrayList<String>();
        a.add("CSDN_SEU_Calvin");
        Class c = a.getClass();
        try{
            Method method = c.getMethod("add",Object.class);
            method.invoke(a,100); //使用add向list添加一个元素100

        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println(a);
    }

}
