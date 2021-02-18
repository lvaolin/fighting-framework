package com.dhy.io;/**
 * Created by lvaolin on 17/10/27.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 缓冲输入文件
 *
 * @author lvaolin
 * @create 17/10/27 下午1:36
 */
public class InputStreamTest {

    public static String read(String filename) throws IOException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String s;
        StringBuffer sb = new StringBuffer();

        while ((s=bufferedReader.readLine())!=null){

            sb.append(s+"\n");
            //System.out.println(s);
            //TimeUnit.SECONDS.sleep(1);
        }

        bufferedReader.close();

        return sb.toString();
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        String res = InputStreamTest.read("/Users/lvaolin/Downloads/C1307_tomcat_stdout.log");
        //System.out.println(res);

    }

}
