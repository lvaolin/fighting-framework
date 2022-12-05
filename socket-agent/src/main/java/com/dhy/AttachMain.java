package com.dhy;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Project socket-agent
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/12/5 上午10:23
 */
public class AttachMain {
    public static void main(String[] args) throws Exception {
        if (args==null||args.length==0) {
            System.out.println("需要参数指定agent jar 位置");
            return;
        }

        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor v : list) {
            System.out.println(v.id()+"   "+v.toString());
        }
        byte[] idbyte = new byte[128];
        System.in.read(idbyte);
        String id = new String(idbyte);
        id = id.trim();
        boolean flag = false;
        for (VirtualMachineDescriptor v : list) {
            if (v.id().endsWith(id)) {
                flag = true;
                break;
            }
        }

        if (flag) {
            VirtualMachine vm = VirtualMachine.attach(id);
            //"/Users/lvaolin/code_github.com/study/fighting-framework/socket-agent/target/socket-agent-jar-with-dependencies.jar"
            System.out.println("loading..."+args[0]);
            vm.loadAgent(args[0]);
        }

        //vm.detach();
        while (true){
            TimeUnit.SECONDS.sleep(3);
            System.out.println("attach 运行中...");
        }
    }
}
