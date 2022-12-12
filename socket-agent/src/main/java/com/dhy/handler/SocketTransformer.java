package com.dhy.handler;

import javassist.*;

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
        classPool.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
        classPool.appendClassPath(new ClassClassPath(this.getClass()));
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
            String myMap = "java.lang.String createTime=java.time.LocalDateTime.now().toString();" +
                    "java.lang.ClassLoader myClassLoader = java.lang.Thread.currentThread().getContextClassLoader();" +//解决父类加载器加载子类问题
                    "java.lang.Class aClass = myClassLoader.loadClass(\"com.dhy.handler.SocketTransformer\");" +
                    "java.lang.reflect.Field myMapField = aClass.getDeclaredField(\"myMap\");" +
                    "java.util.Map myMap = (java.util.Map)myMapField.get(null);";

            connectMethod.insertBefore("" +
                    "java.lang.System.out.println(\"----Socket connect----\");"+
                            myMap+
                    "myMap.put(this,new java.lang.Exception(createTime));"
            );

            CtClass exception = classPool.get("java.lang.Exception");
            connectMethod.addCatch("" +
                    "java.lang.System.out.println(\"----Socket Exception----\");"+
                            myMap+
                    "myMap.remove(this);" +
                    "throw $e;",exception);

            CtMethod closeMethod = socketClass.getDeclaredMethod("close");
            closeMethod.insertBefore("" +
                    "java.lang.System.out.println(\"----Socket close----\");"+
                            myMap+
                    "myMap.remove(this);"

            );
            System.out.println("----Socket字节码增强成功----");
            this.startMonitor();
            return socketClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void startMonitor() {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(300);
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
