package com.dhy.encode;

import java.io.UnsupportedEncodingException;

/**
 * UTF-8编码占用字节测试
 *
 * @author lvaolin
 * @create 2019/9/20 11:59 AM
 */
public class Utf8Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String chinese="a";
        //使用UTF-8编码方式进行编码。
        byte[] uftcn = chinese.getBytes("UTF-8");
        byte[] gbkcn = chinese.getBytes("GBK");
        byte[] iso8859cn = chinese.getBytes("ISO8859-1");
        byte[] asciicn = chinese.getBytes("ASCII");
        for (byte b : uftcn) {
            System.out.print(b+" ");

        }
        System.out.println();
        for (byte b : gbkcn) {
            System.out.print(b+" ");
        }
        System.out.println();
        for (byte b : iso8859cn) {
            System.out.print(b+" ");
        }
        System.out.println();
        for (byte b : asciicn) {
            System.out.print(b+" ");
        }
        System.out.println();
        //使用UTF-8编码方式进行解码。
        System.out.println(new String(uftcn,"UTF-8"));
        System.out.println(new String(gbkcn,"GBK"));
        System.out.println(new String(iso8859cn,"ISO8859-1"));
        System.out.println(new String(asciicn,"ASCII"));

        /*String english="a";
        //使用UTF-8编码方式进行编码。
        byte[] en = english.getBytes("UTF-8");
        for (byte b : en) {
            System.out.println(b+" ");
        }*/
    }
}
