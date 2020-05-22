package com.io;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        String inputfile = "C:\\Users\\41490\\Desktop\\ttt\\a.mp4";
        String outputfile = "C:\\Users\\41490\\Desktop\\ttt\\b.mp4";

        long startTime = System.currentTimeMillis();
        for (int i = 0; i <1 ; i++) {
            fileCopy(new File(inputfile),new File(outputfile));
           // System.out.println(i);
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("fileCopy耗时"+(stopTime-startTime)/1000 +"s");

        String inputfile2 = "C:\\Users\\41490\\Desktop\\ttt\\a.mp4";
        String outputfile2 = "C:\\Users\\41490\\Desktop\\ttt\\c.mp4";

        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i <1 ; i++) {
            fileCopyWithFileChannel(new File(inputfile2),new File(outputfile2));
            //System.out.println(i);
        }
        long stopTime2 = System.currentTimeMillis();
        System.out.println("fileCopyWithFileChannel耗时"+(stopTime2-startTime2)/1000 +"s");

    }


    static  void  fileCopyWithFileChannel(File from,File to) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(from);
        FileChannel fileInputChannel = fileInputStream.getChannel();
        fileInputChannel.force(true);
        FileOutputStream fileOutputStream = new FileOutputStream(to);
        FileChannel fileOutputChannel = fileOutputStream.getChannel();
        fileOutputChannel.force(true);

        int position = 0;
        long size = fileInputChannel.size();
        while (0 < size) {
            //注意transferTo 一次只能处理最大2G的长度（源码写死的），所以要循环传递，否则大于2G的部分会丢失
            long count = fileInputChannel.transferTo(position, size, fileOutputChannel);
            if (count > 0) {
                position += count;
                size -= count;
            }
        }

        fileInputChannel.close();
        fileOutputChannel.close();
    }

    static void fileCopy(File from ,File to) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(from));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(to));
        byte[] bytes = new byte[20240];

        int len = 0;
        while ((len=bufferedInputStream.read(bytes))!=-1){
            bufferedOutputStream.write(bytes,0,len);
        }

        bufferedOutputStream.close();
        bufferedInputStream.close();

    }
}
