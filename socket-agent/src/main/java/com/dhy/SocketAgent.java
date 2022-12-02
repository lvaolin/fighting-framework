package com.dhy;


import java.lang.instrument.Instrumentation;

/**
 * @Project socket-agent
 * @Description Socket泄漏排查
 * @Author lvaolin
 * @Date 2022/11/30 下午9:48
 */
public class SocketAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            byte[] bytecode = TransformerFactory.getBytecode(className);
            return bytecode==null?classfileBuffer:bytecode;
        });
    }
}