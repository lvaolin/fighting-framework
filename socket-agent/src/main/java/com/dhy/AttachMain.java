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
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        VirtualMachine vm = null;
        for (VirtualMachineDescriptor v : list) {
            if (v.toString().contains("com.dhy.demo.spring.mybatis.MybatisApplication")) {
                vm = VirtualMachine.attach(v);
                vm.loadAgent("/Users/lvaolin/code_github.com/study/fighting-framework/socket-agent/target/socket-agent-jar-with-dependencies.jar");
                break;
            }
        }
        //vm.detach();
        while (true){
            TimeUnit.SECONDS.sleep(3);
            System.out.println("attach 运行中...");
        }
    }
}
