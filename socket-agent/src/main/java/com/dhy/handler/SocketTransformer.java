package com.dhy.handler;

import javassist.*;

import java.io.IOException;

/**
 * @Project socket-agent
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/12/2 上午11:41
 */
public class SocketTransformer implements ITransformer {

    @Override
    public byte[] transform() {
        System.out.println("----开始Socket植入----");
        ClassPool classPool = ClassPool.getDefault();
        try {
            CtClass socketClass = classPool.get("java.net.Socket");
            CtClass socketAddressClass = classPool.get("java.net.SocketAddress");
            CtClass concurrentHashMapClass = classPool.get("java.util.concurrent.ConcurrentHashMap");

            CtClass mapClass = classPool.get("java.util.Map");
            CtField socketMapField = new CtField(mapClass, "myMap", socketClass);
            socketMapField.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
            CtField.Initializer initializer = CtField.Initializer.byNew(concurrentHashMapClass);
            socketClass.addField(socketMapField, initializer);

            CtMethod connectMethod = socketClass.getDeclaredMethod("connect", new CtClass[]{socketAddressClass, CtClass.intType});
            connectMethod.insertBefore("" +
                    "java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n" +
                    "java.util.Date date=new java.util.Date();" +
                    "java.lang.String createTime=simpleDateFormat.format(date);" +
                    "myMap.put(this,new java.lang.Exception(createTime));");

            CtClass exception = classPool.get("java.lang.Exception");
            connectMethod.addCatch("myMap.remove(this);throw $e;",exception);

            CtMethod closeMethod = socketClass.getDeclaredMethod("close");
            closeMethod.insertBefore("myMap.remove(this);");
            System.out.println("----植入Socket成功----");
            return socketClass.toBytecode();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
