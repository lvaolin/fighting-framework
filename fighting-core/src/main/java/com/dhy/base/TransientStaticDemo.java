package com.dhy.base;/**
 一个user对象  ，，先序列化到文件，再从文件读入内存，以此来验证 transient修饰的实例成员变量不会序列化
 static修饰的变量不会被序列化
 * Created by lvaolin on 17/10/17.
 */

import java.io.*;

/**
 * @author lvaolin
 * @create 17/10/17 下午3:20
 */
public class TransientStaticDemo {

    public static void main(String[] args) {

        User user = new TransientStaticDemo.User();
        user.setUsername("Alexia");
        user.setPasswd("123456");

        System.out.println("read before Serializable: ");
        System.out.println("username: " + user.getUsername());
        System.err.println("password: " + user.getPasswd());

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/Users/lvaolin/Downloads/javatest.txt"));
            os.writeObject(user); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            User.username="lvvvvvvvv";//反序列化后仍然是这个值 证明了staic修饰的变量不会被序列化
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("/Users/lvaolin/Downloads/javatest.txt"));
            user = (User) is.readObject(); // 从流中读取User的数据
            is.close();

            System.out.println("\n read after Serializable: ");
            System.out.println("username: " + user.getUsername());
            System.err.println("password: " + user.getPasswd());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    static class User implements Serializable {
        private static final long serialVersionUID = 8294180014912103005L;

        //static修饰的变量不会被序列化
        private static  String username;
        //transient修饰的变量不会持久化不会序列化
        private transient String passwd;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

    }

}
