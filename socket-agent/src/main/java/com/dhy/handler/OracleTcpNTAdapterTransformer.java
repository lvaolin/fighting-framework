package com.dhy.handler;

import javassist.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Project socket-agent
 * @Description 只针对18.3.0.0版本进行了测试
 *
 *          <dependency>
 *             <groupId>com.oracle.jdbc</groupId>
 *             <artifactId>ojdbc8</artifactId>
 *             <version>18.3.0.0</version>
 *         </dependency>
 *
 * @Author lvaolin
 * @Date 2022/12/2 下午22:41
 */
public class OracleTcpNTAdapterTransformer implements ITransformer,IMonitor {

    public static Map myMap = new ConcurrentHashMap();

    @Override
    public byte[] transform() {

        System.out.println("----开始OracleTcpNTAdapter字节码增强----");
        ClassPool classPool = ClassPool.getDefault();
        classPool.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
        classPool.appendClassPath(new ClassClassPath(this.getClass()));

        try {
            CtClass socketClass = classPool.get("oracle.net.nt.TcpNTAdapter");
            //CtClass socketAddressClass = classPool.get("java.net.SocketAddress");
            CtClass concurrentHashMapClass = classPool.get("java.util.concurrent.ConcurrentHashMap");

            CtClass mapClass = classPool.get("java.util.Map");
            //CtField myMap = new CtField(mapClass, "myMap", socketClass);
            //myMap.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
            CtField.Initializer initializer = CtField.Initializer.byNew(concurrentHashMapClass);
            //socketClass.addField(myMap, initializer);

            CtMethod connectMethod = socketClass.getDeclaredMethod("connect");

            String myMap = "" +
                    "java.lang.String createTime=java.time.LocalDateTime.now().toString();" +
                    "java.lang.ClassLoader myClassLoader = java.lang.Thread.currentThread().getContextClassLoader();" +//解决父类加载器加载子类问题
                    "java.lang.Class aClass = myClassLoader.loadClass(\"com.dhy.handler.OracleTcpNTAdapterTransformer\");" +
                    "java.lang.reflect.Field myMapField = aClass.getDeclaredField(\"myMap\");" +
                    "java.util.Map myMap = (java.util.Map)myMapField.get(null);";

            connectMethod.insertBefore("" +
                    "java.lang.System.out.println(\"----OracleTcpNTAdapter connect----\");"+
                    myMap +
                    "myMap.put(this,new java.lang.Exception(createTime));"
            );

            CtClass exception = classPool.get("java.lang.Exception");
            connectMethod.addCatch("" +
                    myMap+
                    "myMap.remove(this);" +
                    "java.lang.System.out.println(\"----OracleTcpNTAdapter exception----\");"+
                    "throw $e;"
                    ,exception);

            CtMethod closeMethod = socketClass.getDeclaredMethod("disconnect");
            closeMethod.insertBefore("" +
                    myMap+
                    "myMap.remove(this);"+
                    "java.lang.System.out.println(\"----OracleTcpNTAdapter disconnect----\");"
            );
            System.out.println("----OracleTcpNTAdapter字节码增强成功----");

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
