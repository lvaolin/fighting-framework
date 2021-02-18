package com.dhy.file;

import java.io.File;
import java.io.IOException;

/**
 * 看下文件描述符信息
 */
public class FdDemo1 {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\tmp\\fddemo1.txt");
        file.setExecutable(false);
        file.setReadable(false);
        System.out.println("canExecute:"+file.canExecute());
        System.out.println("canRead:"+file.canRead());
        System.out.println("canWrite:"+file.canWrite());
        System.out.println(file.getParent());
        System.out.println(file.getCanonicalFile());
    }
}
