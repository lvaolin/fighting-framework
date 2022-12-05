package com.dhy.handler;

import javassist.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Project socket-agent
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/12/2 上午11:41
 */
public class SocketTransformer implements ITransformer,IMonitor {
    public static Map myMap = new ConcurrentHashMap();

    @Override
    public byte[] transform() {
        System.out.println("----开始Socket字节码增强----");
        ClassPool classPool = ClassPool.getDefault();
        try {
            CtClass socketClass = classPool.get("java.net.Socket");
            CtClass socketAddressClass = classPool.get("java.net.SocketAddress");
            //CtClass concurrentHashMapClass = classPool.get("java.util.concurrent.ConcurrentHashMap");

            //CtClass mapClass = classPool.get("java.util.Map");
            //CtField socketMapField = new CtField(mapClass, "myMap", socketClass);
            //socketMapField.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
            //CtField.Initializer initializer = CtField.Initializer.byNew(concurrentHashMapClass);
            //socketClass.addField(socketMapField, initializer);

            CtMethod connectMethod = socketClass.getDeclaredMethod("connect", new CtClass[]{socketAddressClass, CtClass.intType});
            connectMethod.insertBefore("" +
                    "java.lang.System.out.println(\"----connect----\");"+
                    "java.lang.String createTime=java.time.LocalDateTime.now().toString();" +
                    "com.dhy.handler.SocketTransformer.myMap.put(this,new java.lang.Exception(createTime));"
            );

            CtClass exception = classPool.get("java.lang.Exception");
            connectMethod.addCatch("com.dhy.handler.SocketTransformer.myMap.remove(this);throw $e;",exception);

            CtMethod closeMethod = socketClass.getDeclaredMethod("close");
            closeMethod.insertBefore("com.dhy.handler.SocketTransformer.myMap.remove(this);");
            System.out.println("----Socket字节码增强成功----");
            this.startMonitor();
            return socketClass.toBytecode();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void startMonitor() {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printLog();
            }
        }).start();
    }



    private void printLog() {
        try {
            Map<Object, Exception> map = myMap;
            String myName = this.getClass().getName();
            System.out.println("遗留"+myName+"连接：" + map.size() + "个");
            map.forEach((s, e) -> {
                System.out.println(s.toString() + "---"+myName+"->");
                System.out.println(e.getMessage() + "---"+myName+"->");
                e.printStackTrace();
            });
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
