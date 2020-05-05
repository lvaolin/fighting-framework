/*
package com.dhy.attach;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import sun.tools.attach.HotSpotVirtualMachine;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

*/
/**
 * attach demo
 *
 * @author lvaolin
 * @create 2020/1/17 10:40 AM
 *//*

public class Demo01 {
    public static void main(String[] args) throws Exception{
        HotSpotVirtualMachine attach = (HotSpotVirtualMachine)VirtualMachine.attach("49970");
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        InputStream in1 = attach.dumpHeap((Object[]) args);
        printStream(in1);
        System.out.println("----------------------------------");
        InputStream in2 = attach.remoteDataDump((Object[])args);
        printStream(in2);
        attach.detach();
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
*/
