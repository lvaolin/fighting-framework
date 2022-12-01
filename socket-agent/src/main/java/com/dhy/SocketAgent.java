package com.dhy;

import javassist.*;

import java.io.IOException;
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
            if (className.equals("java/net/Socket")) {
                System.out.println("----开始植入----");
                ClassPool classPool = ClassPool.getDefault();
                try {
                    CtClass socketClass = classPool.get("java.net.Socket");
                    CtClass socketAddressClass = classPool.get("java.net.SocketAddress");
                    CtClass concurrentHashMapClass = classPool.get("java.util.concurrent.ConcurrentHashMap");

                    CtClass mapClass = classPool.get("java.util.Map");
                    CtField socketMapField = new CtField(mapClass, "socketMap", socketClass);
                    socketMapField.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
                    CtField.Initializer initializer = CtField.Initializer.byNew(concurrentHashMapClass);
                    socketClass.addField(socketMapField, initializer);

                    CtMethod connectMethod = socketClass.getDeclaredMethod("connect", new CtClass[]{socketAddressClass, CtClass.intType});
                    connectMethod.insertAfter("" +
                            "java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n" +
                            "java.util.Date date=new java.util.Date();" +
                            "java.lang.String createTime=simpleDateFormat.format(date);" +
                            "socketMap.put(this,new java.lang.Exception(createTime));");

                    CtMethod closeMethod = socketClass.getDeclaredMethod("close");
                    closeMethod.insertAfter("socketMap.remove(this);");
                    System.out.println("----植入成功----");
                    return socketClass.toBytecode();
                } catch (NotFoundException | CannotCompileException | IOException e) {
                    e.printStackTrace();
                }
            }
            return classfileBuffer;
        });

    }


}