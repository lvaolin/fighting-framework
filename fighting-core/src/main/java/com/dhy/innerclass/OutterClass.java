package com.dhy.innerclass;/**
 * Created by lvaolin on 17/10/18.
 */

/**
 * 非静态内部类
 *
 * @author lvaolin
 * @create 17/10/18 上午9:34
 */
public class OutterClass {


    private int pid = 100;
    private String pname="十九大期间不许乱说话";
    private static String ppwd="12345";

    private void outterMethod(){

    }

    private static void outterStaticMethod(){

    }

    public static void main(String[] args) {
        OutterClass outterClass = new OutterClass();
       InnerClass innerClass = outterClass.new InnerClass();
       innerClass.methoda();
    }



    class InnerClass{
        private int id;
        private  String name;
        public void methoda(){
            id = pid;
            name = pname;
            System.out.println(id+"~~~~~"+name);
            outterMethod();
            outterStaticMethod();
        }



    }


   static class StaticInnerClass{
        private int id;
        private  String name;
        public static void methoda(){
           // id = pid;
           // name = ppwd;
            // System.out.println(pid+"~~~~~"+ppwd);//不能引用外部类的非静态成员
        }

       public  void methodb(){
           // id = pid;
           // name = ppwd;
           // System.out.println(pid+"~~~~~"+ppwd);//不能引用外部类的非静态成员
       }

    }

}
