package com.dhy.handler;

import javassist.*;

import java.io.IOException;

/**
 * @Project socket-agent
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/12/2 上午11:41
 */
public class SocketChannelImplTransformer implements ITransformer {

    @Override
    public byte[] transform() {

        System.out.println("----开始SocketChannel植入----");
        ClassPool classPool = ClassPool.getDefault();
        try {
            CtClass socketClass = classPool.get("sun.nio.ch.SocketChannelImpl");
            CtClass socketAddressClass = classPool.get("java.net.SocketAddress");
            CtClass concurrentHashMapClass = classPool.get("java.util.concurrent.ConcurrentHashMap");

            CtClass mapClass = classPool.get("java.util.Map");
            CtField socketMapField = new CtField(mapClass, "myMap", socketClass);
            socketMapField.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
            CtField.Initializer initializer = CtField.Initializer.byNew(concurrentHashMapClass);
            socketClass.addField(socketMapField, initializer);

            CtMethod connectMethod = socketClass.getDeclaredMethod("connect", new CtClass[]{socketAddressClass});
            connectMethod.insertBefore("" +
                    "java.lang.String createTime=java.time.LocalDateTime.now().toString();" +
                    "myMap.put(this,new java.lang.Exception(createTime));");

            CtMethod closeMethod = socketClass.getDeclaredMethod("finishConnect");
            closeMethod.insertAfter("myMap.remove(this);");
            System.out.println("----植入SocketChannel成功----");
            return socketClass.toBytecode();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
