package com.dhy.handler;

import javassist.*;

import java.io.IOException;

/**
 * @Project socket-agent
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/12/2 下午22:41
 */
public class OracleTcpNTAdapterTransformer implements ITransformer {

    @Override
    public byte[] transform() {

        System.out.println("----开始OracleTcpNTAdapter植入----");
        ClassPool classPool = ClassPool.getDefault();
        try {
            CtClass socketClass = classPool.get("oracle.net.nt.TcpNTAdapter");
            CtClass socketAddressClass = classPool.get("java.net.SocketAddress");
            CtClass concurrentHashMapClass = classPool.get("java.util.concurrent.ConcurrentHashMap");

            CtClass mapClass = classPool.get("java.util.Map");
            CtField myMap = new CtField(mapClass, "myMap", socketClass);
            myMap.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
            CtField.Initializer initializer = CtField.Initializer.byNew(concurrentHashMapClass);
            socketClass.addField(myMap, initializer);

//            CtConstructor[] declaredConstructors = socketClass.getDeclaredConstructors();
//            for (CtConstructor declaredConstructor : declaredConstructors) {
//
//                declaredConstructor.insertBefore(""+
//                        "java.lang.System.out.println(\"----create----\");"
//
//                );
//            }

            CtMethod connectMethod = socketClass.getDeclaredMethod("connect");
            connectMethod.insertBefore("" +
                    "java.lang.System.out.println(\"----connect----\");"+
                    "java.lang.String createTime=java.time.LocalDateTime.now().toString();" +
                    "myMap.put(this,new java.lang.Exception(createTime));"
            );

            CtClass exception = classPool.get("java.lang.Exception");
            connectMethod.addCatch("myMap.remove(this);" +
                            "java.lang.System.out.println(\"----exception----\");"+
                    "throw $e;",exception);

            CtMethod closeMethod = socketClass.getDeclaredMethod("disconnect");
            closeMethod.insertBefore("myMap.remove(this);"+
                    "java.lang.System.out.println(\"----disconnect----\");"
            );
            System.out.println("----植入OracleTcpNTAdapter成功----");
            return socketClass.toBytecode();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
