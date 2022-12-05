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
 * @Date 2022/12/2 下午16:41
 */
public class SocketAdaptorTransformer implements ITransformer,IMonitor {
    public static Map myMap = new ConcurrentHashMap();

    @Override
    public byte[] transform() {

        System.out.println("----开始SocketAdaptor植入----");
        ClassPool classPool = ClassPool.getDefault();
        try {
            CtClass socketClass = classPool.get("sun.nio.ch.SocketAdaptor");
            CtClass socketAddressClass = classPool.get("java.net.SocketAddress");

            CtMethod connectMethod = socketClass.getDeclaredMethod("connect", new CtClass[]{socketAddressClass, CtClass.intType});
            connectMethod.insertBefore("" +
                    "java.lang.System.out.println(\"----connect----\");"+
                    "java.lang.String createTime=java.time.LocalDateTime.now().toString();" +
                    "com.dhy.handler.SocketAdaptorTransformer.myMap.put(this,new java.lang.Exception(createTime));"
            );

            CtClass exception = classPool.get("java.lang.Exception");
            connectMethod.addCatch("com.dhy.handler.SocketAdaptorTransformer.myMap.remove(this);" +
                            "java.lang.System.out.println(\"----exception----\");"+
                    "throw $e;",exception);

            CtMethod closeMethod = socketClass.getDeclaredMethod("close");
            closeMethod.insertBefore("com.dhy.handler.SocketAdaptorTransformer.myMap.remove(this);"+
                    "java.lang.System.out.println(\"----close----\");"
            );
            System.out.println("----植入SocketAdaptor成功----");

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
