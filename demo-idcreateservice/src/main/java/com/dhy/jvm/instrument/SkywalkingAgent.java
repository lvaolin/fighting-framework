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


    public static void premain(String options, Instrumentation ins) {

        if (options != null) {
            System.out.printf("  I've been called with options: \"%s\"\n",
                    options);
        } else{

            System.out.println("  I've been called with no options.");
        }
        ins.addTransformer(new SkywalkingAgent());

    }
}
