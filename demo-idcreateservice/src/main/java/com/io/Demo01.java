package com.io;

import java.io.*;

/**
 * 实现从一个文件读取数据写入到另一个文件
 *
 * @author lvaolin
 * @create 2020/1/21 3:39 PM
 */
public class Demo01 {

    public static void transFileWithByteStream(String from,String to){
        try {
            FileInputStream inputStream = new FileInputStream(new File(from));
            FileOutputStream outputStream = new FileOutputStream(new File(to));
            byte[] result = new byte[1024];
            while (inputStream.read(result)!=-1) {
                outputStream.write(result);
            }

            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void transFileWithCharStream(String from,String to){
        try {
            FileReader inputStream = new FileReader(new File(from));
            FileWriter outputStream = new FileWriter(new File(to));
            char[] result = new char[512];
            while (inputStream.read(result)!=-1) {
                for (char c : result) {
                    System.out.print(c);
                }
                //outputStream.write(result);
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
        String inputfile = "/Users/lvaolin/Downloads/inputfile.txt";
        String outputfile = "/Users/lvaolin/Downloads/outfile.txt";
        transFileWithByteStream(inputfile,outputfile);
        String outputfilechar = "/Users/lvaolin/Downloads/outfilechar.txt";
        transFileWithCharStream(inputfile,outputfilechar);
    }
}
