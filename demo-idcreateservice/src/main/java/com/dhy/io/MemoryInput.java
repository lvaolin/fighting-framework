package com.dhy.io;/**
 * Created by lvaolin on 17/10/27.
 */

import java.io.IOException;
import java.io.StringReader;

/**
 * 从内存读取
 *
 * @author lvaolin
 * @create 17/10/27 下午1:47
 */
public class MemoryInput {

    public static void main(String[] args) throws IOException, InterruptedException {
        StringReader stringReader = new StringReader(InputStreamTest.read("/Users/lvaolin/Downloads/C1307_tomcat_stdout.log"));

        int c;
        while ((c=stringReader.read())!=-1){
            System.out.println((char) c);
        }

    }


}
