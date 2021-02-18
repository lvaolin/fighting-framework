package com.dhy.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

import java.net.URI;

/**
 *演示向hdfs系统中存储文件
 */
public class Put {


    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://192.168.197.131:9000"),conf,"root");
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path("/"),true);
       while (iterator.hasNext()){
           LocatedFileStatus ss = iterator.next();
           System.out.println(ss.isFile()?"file":"dir");
           System.out.println(":"+ss.getOwner()+":"+ss.getPath().getName()+":"+ss.getLen());
       }
        fs.close();
    }
}
