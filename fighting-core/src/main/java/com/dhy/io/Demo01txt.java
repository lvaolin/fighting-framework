package com.dhy.io;

import java.io.*;

/**
 * 实现从一个文件读取数据写入到另一个文件
 * txt文件是字符文件，所以使用字节流或者字符流都可以正确传输
 * @author lvaolin
 * @create 2020/1/21 3:39 PM
 */
public class Demo01txt {

    /**
     * 基于字节流传输
     * @param from
     * @param to
     */
    public static void transFileWithByteStream(String from,String to){
        try {
            FileInputStream inputStream = new FileInputStream(new File(from));
            FileOutputStream outputStream = new FileOutputStream(new File(to));
            byte[] result = new byte[1024];
            int len =0;
            while ( (len=inputStream.read(result))!=-1) {
                outputStream.write(result,0,len); //注意指定len长度，否则最后一次读取可能无法填满数组，导致上一次的旧数据被输出

            }

            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 基于字符流传输
     * @param from
     * @param to
     */
    public static void transFileWithCharStream(String from,String to){
        try {
            FileReader inputStream = new FileReader(new File(from));
            FileWriter outputStream = new FileWriter(new File(to));
            char[] result = new char[512];
            int len =0;
            while ((len = inputStream.read(result))!=-1) {
                outputStream.write(result,0,len); //注意指定len长度，否则最后一次读取可能无法填满数组，导致上一次的旧数据被输出
            }
            inputStream.close();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String inputfile = "C:\\tmp\\fddemo1.txt";
        String outputfile = "C:\\tmp\\fddemo2.txt";
        transFileWithByteStream(inputfile,outputfile);
        String outputfilechar = "C:\\Users\\41490\\Desktop\\c.txt";
        transFileWithCharStream(inputfile,outputfilechar);
    }
}
