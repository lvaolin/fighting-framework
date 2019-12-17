package com.io;/**
 * Created by lvaolin on 17/10/27.
 */

import java.io.*;

/**
 * 输出到文件
 *
 * @author lvaolin
 * @create 17/10/27 下午2:43
 */
public class BasicFileOutPut {


    static String outfile = "/Users/lvaolin/Downloads/C1307_tomcat_stdout.log.bak";

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new StringReader(InputStreamTest.read("/Users/lvaolin/Downloads/C1307_tomcat_stdout.log")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outfile)));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }
        out.close();
        System.out.println(InputStreamTest.read(outfile));
    }
}
