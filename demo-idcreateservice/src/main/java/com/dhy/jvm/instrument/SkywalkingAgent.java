package com.dhy.jvm.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author 41490
 */
public class SkywalkingAgent  implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("transform-------------");
        if(!className.endsWith("HelloWorld")) {
            return(null);
        }

        String line="";
        for(int i=0;i<classfileBuffer.length;i++){
            line +=Byte.toString(classfileBuffer[i])+" ";
            if(line.length()>60){
                System.out.println(line);
                line="";
            }
            if(classfileBuffer[i]==(byte)'6')
                classfileBuffer[i]=(byte)'7';
        }
        System.out.println(line);
        System.out.println("The number of bytes in HelloWorld: "+classfileBuffer.length);
        return(classfileBuffer);
    }

    /**
     * 在主程序运行之前的代理程序
     * 修改：MANIFEST.MF
     * 增加JVM参数：-javaagent: 你的路径/test-1.0-SNAPSHOT.jar=parameterString
     * 其中parameterString为上文中传入permain方法的agentArgs参数
     * @param options
     * @param ins
     */
    public static void premain(String options, Instrumentation ins) {
        System.out.println("premain-------------");
        if (options != null) {
            System.out.printf("  I've been called with options: \"%s\"\n",
                    options);
        } else{

            System.out.println("  I've been called with no options.");
        }
        ins.addTransformer(new SkywalkingAgent());

    }

    /**
     * 用于在主程序运行之后的代理程序
     * @param options
     * @param ins
     */
    public static void agentmain(String options, Instrumentation ins) {

        System.out.println("agentmain-------------");
        Class[] allLoadedClasses = ins.getAllLoadedClasses();
        for (Class allLoadedClass : allLoadedClasses) {
            System.out.println(allLoadedClass.getName());
        }

    }
}
