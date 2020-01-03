package com.asm;

import javassist.*;

import java.io.IOException;

/**
 * 通过javassist实现字节码增强（aop）
 * @author lvaolin
 * @create 2020/1/3 5:26 PM
 */
public class JavassistTest {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.asm.HelloWorld");
        CtMethod m = cc.getDeclaredMethod("sayHello");
        m.insertBefore(" System.out.println(\"start\"); ");
        m.insertAfter(" System.out.println(\"end\"); ");
        Class c = cc.toClass();
        cc.writeFile("/Users/lvaolin/Downloads");
        HelloWorld h = (HelloWorld)c.newInstance();
        h.sayHello();
    }

}
