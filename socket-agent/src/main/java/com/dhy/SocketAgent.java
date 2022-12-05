package com.dhy;


import com.dhy.handler.TransformerFactory;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @Project socket-agent
 * @Description Socket泄漏排查
 * @Author lvaolin
 * @Date 2022/11/30 下午9:48
 */
public class SocketAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("---premain---");
        doPremain(inst);
    }
    public static void agentmain(String args, Instrumentation inst) throws UnmodifiableClassException, ClassNotFoundException {
        System.out.println("---agentmain---");
        doAgentmain(inst);
    }
    private static void doPremain(Instrumentation inst) {
        inst.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            byte[] bytecode = TransformerFactory.getBytecode(className);
            return bytecode==null?classfileBuffer:bytecode;
        });
    }
    private static void doAgentmain(Instrumentation inst) throws UnmodifiableClassException, ClassNotFoundException {
        redefineClasses(inst);
    }
    private static void redefineClasses(Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException {
        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class aClass : allLoadedClasses) {
            byte[] bytes = TransformerFactory.getBytecode(aClass.getName().replaceAll("\\.","/"));
            if (bytes==null) {
                continue;
            }

            ClassDefinition classDefinition = new ClassDefinition(aClass, bytes);
            inst.redefineClasses(classDefinition);
            System.out.println(aClass.getName()+" 字节码替换成功");
        }
    }

}