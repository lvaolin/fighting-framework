package com.dhy.attach;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import sun.tools.attach.HotSpotVirtualMachine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * attach demo
 *
 * @author lvaolin
 * @create 2020/1/17 10:40 AM*/


public class Demo01 {
    public static void main(String[] args) throws Exception{
        //attach   demo.MathGame
        //jps列表
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        VirtualMachineDescriptor target = null;
        for (VirtualMachineDescriptor virtualMachineDescriptor : list) {
            System.out.println(virtualMachineDescriptor.displayName());
            if (virtualMachineDescriptor.displayName().equalsIgnoreCase("demo.MathGame")) {
                target = virtualMachineDescriptor;
                break;
            }
        }
        if (target==null) {
            throw new RuntimeException("not find");
        }
//        HotSpotVirtualMachine attach = (HotSpotVirtualMachine)VirtualMachine.attach("34174");
//        HotSpotVirtualMachine attach = (HotSpotVirtualMachine)VirtualMachine.attach(target);
//        InputStream in1 = attach.dumpHeap((Object[]) args);
//        printStream(in1);
//        System.out.println("----------------------------------");
//        InputStream in2 = attach.remoteDataDump((Object[])args);
//        printStream(in2);
//        attach.detach();

        //获取目标进程信息
        printVirtualMachineDescriptor(target);

        VirtualMachine virtualMachine = VirtualMachine.attach(target);
        //获取JVM系统属性
        printProperties(virtualMachine.getSystemProperties());
        //获取JVM参数
        printProperties(virtualMachine.getAgentProperties());
        virtualMachine.loadAgent("/Users/lvaolin/code_github.com/study/idcreateservice/demo-idcreateservice/target/demo-idcreateservice-1.0-SNAPSHOT.jar");
        virtualMachine.startLocalManagementAgent();
        synchronized (Demo01.class){
            Demo01.class.wait();
        }
    }

    static void   printVirtualMachineDescriptor(VirtualMachineDescriptor vmd){
        System.out.println("-------------------------------");
        //进程id
        System.out.println(vmd.id());
        //进程名称
        System.out.println(vmd.displayName());
        System.out.println(vmd.toString());
    }
    static void  printProperties(Properties systemProperties){
        System.out.println("-------------------------------");
        Enumeration<?> enumeration = systemProperties.propertyNames();
        while (enumeration.hasMoreElements()){
            Object o = enumeration.nextElement();
            System.out.println(o.toString()+":"+systemProperties.getProperty(o.toString()));
        }
    }


    private static void printStream( InputStream in) throws IOException {

        byte b[] = new byte[256];
        int n = 0;
        do {
            n = in.read(b);
            if (n > 0) {
                System.out.println(new String(b, 0, n));
            }
        } while (n > 0);
        in.close();
    }
}
