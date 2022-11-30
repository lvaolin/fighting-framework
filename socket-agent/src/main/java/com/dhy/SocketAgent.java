package com.dhy;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

public class SocketAgent {
    public static void premain(String agentArgs, Instrumentation inst) {

        inst.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            if(className.equals("java/net/Socket")){
                ClassPool aDefault = ClassPool.getDefault();

                CtClass ctClass = null;
                try {
                    ctClass = aDefault.get("java.net.Socket");
                    CtClass param1 = aDefault.get("java.net.SocketAddress");
                    CtClass concurrentHashMapClass=aDefault.get("java.util.concurrent.ConcurrentHashMap");

                    CtClass mapClass=aDefault.get("java.util.Map");
                    CtField monitor = new CtField(mapClass, "socketMap", ctClass);
                    monitor.setModifiers(Modifier.STATIC|Modifier.PUBLIC);
                    CtField.Initializer initializer = CtField.Initializer.byNew(concurrentHashMapClass);
                    ctClass.addField(monitor,initializer);

                    CtMethod connect = ctClass.getDeclaredMethod("connect", new CtClass[]{param1, CtClass.intType});
                    connect.insertAfter("" +
                            "java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n" +
                            "java.util.Date date=new java.util.Date();"+
                            "java.lang.String createTime=simpleDateFormat.format(date);" +
                            "socketMap.put(this,new java.lang.Exception(createTime));");

                    CtMethod close = ctClass.getDeclaredMethod("close");
                    close.insertAfter("socketMap.remove(this);");
                    System.out.println("----植入成功----");
                    return ctClass.toBytecode();
                } catch (NotFoundException | CannotCompileException | IOException e) {
                    e.printStackTrace();
                }
            }
            return classfileBuffer;
        });

    }


}