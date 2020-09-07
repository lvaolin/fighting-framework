package com.dhy.serialization;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Bytes;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.serialize.Serialization;
import org.apache.dubbo.common.serialize.hessian2.Hessian2ObjectInput;
import org.apache.dubbo.common.serialize.hessian2.Hessian2ObjectOutput;
import org.apache.dubbo.common.serialize.nativejava.NativeJavaObjectOutput;
import org.apache.dubbo.common.serialize.nativejava.NativeJavaSerialization;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class NativeJavaSerializationDemo {

    @Test
    public void test() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2ObjectOutput hessian2ObjectOutput = new Hessian2ObjectOutput(byteArrayOutputStream);
        hessian2ObjectOutput.writeObject(list.subList(1,2));
        hessian2ObjectOutput.flushBuffer();

        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(byteArray));
    }

    @Test
    public void test1() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        NativeJavaObjectOutput nj = new NativeJavaObjectOutput(byteArrayOutputStream);
        nj.writeObject(list.subList(1,2));
        nj.flushBuffer();

        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(byteArray));
    }

    @Test
    public void serializationSPI() throws IOException {
        URL url = URL.valueOf("dubbo://192.168.99.1:20880/org.didinem.api.TestApiService?anyhost=true&application=play-dubbo-provider&dubbo=2.5.3&interface=org.didinem.api.TestApiService&methods=setService,mapService&pid=12632&revision=1.0-SNAPSHOT&side=provider&timestamp=1504442138354");
        ExtensionLoader<Serialization> extensionLoader = ExtensionLoader.getExtensionLoader(Serialization.class);
        Serialization hessian2Serialization = extensionLoader.getExtension("hessian2");

        File file = new File("D:\\aaa.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
       // ObjectOutput objectOutput = hessian2Serialization.serialize(url, fileOutputStream);
        //Person person = new Person(1, "aaa");
        //objectOutput.writeObject(person);
       // objectOutput.flushBuffer();

    }

    @Test
    public void mapTest() throws IOException {
        Map<String, String> map = Maps.newHashMap();
        map.put("aa", "11");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2ObjectOutput hessian2ObjectOutput = new Hessian2ObjectOutput(byteArrayOutputStream);
        hessian2ObjectOutput.writeObject(map);
        hessian2ObjectOutput.flushBuffer();

        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(byteArray));

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        Hessian2ObjectInput hessian2ObjectInput = new Hessian2ObjectInput(byteArrayInputStream);
        Map<String, String> result = (Map<String, String>) hessian2ObjectInput.readObject();
        System.out.println(result);

    }

    @Test
    public void setTest() throws IOException, NoSuchMethodException, ClassNotFoundException {
        int[] arrays = new int[]{2, 4, 5, 7, 1, 3, 6};
        Set<Integer> set = Sets.newLinkedHashSet();
        for (int element : arrays) {
            set.add(element);
        }
        System.out.println(set);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2ObjectOutput hessian2ObjectOutput = new Hessian2ObjectOutput(byteArrayOutputStream);
        hessian2ObjectOutput.writeObject(set);
        hessian2ObjectOutput.flushBuffer();

        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(byteArray));

        Class clazz = NativeJavaSerializationDemo.class;
        Method method = clazz.getDeclaredMethod("setService", int[].class);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        Hessian2ObjectInput hessian2ObjectInput = new Hessian2ObjectInput(byteArrayInputStream);
//        Set<Integer> result = (Set<Integer>) hessian2ObjectInput.readObject();
        Object result = hessian2ObjectInput.readObject(method.getReturnType(), method.getGenericReturnType());
        System.out.println(result);
    }

    @Test
    public void serverSerializeResult() throws IOException {
        byte[] bytes = new byte[]{-38, -69, 2, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, -111, 119, 23, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 76, 105, 110, 107, 101, 100, 72, 97, 115, 104, 83, 101, 116, -110, -108, -107, -105, -111, -109, -106};

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Hessian2ObjectInput hessian2ObjectInput = new Hessian2ObjectInput(byteArrayInputStream);
        Object result = hessian2ObjectInput.readObject();
        System.out.println(result);
    }

    @Test
    public void clientDecode() throws IOException {
        byte[] bytes = new byte[]{-38, -69, 2, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, -111, 119, 23, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 76, 105, 110, 107, 101, 100, 72, 97, 115, 104, 83, 101, 116, -110, -108, -107, -105, -111, -109, -106};
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        URL url = URL.valueOf("dubbo://10.115.1.88:20880/org.didinem.api.TestApiService?anyhost=true&application=play-dubbo-consumer&check=false&codec=dubbo&default.check=false&dubbo=2.5.3&heartbeat=60000&interface=org.didinem.api.TestApiService&methods=setService,mapService&monitor=dubbo%3A%2F%2F192.168.0.107%3A7071%3Fdubbo%3D2.5.3%26interface%3Dcom.alibaba.dubbo.monitor.MonitorService%26pid%3D8208%26timestamp%3D1504527533648&pid=8208&retries=0&revision=1.0-SNAPSHOT&side=consumer&timeout=1000000&timestamp=1504527533550");

        ExtensionLoader<Serialization> extensionLoader = ExtensionLoader.getExtensionLoader(Serialization.class);
        Serialization hessian2Serialization = extensionLoader.getExtension("hessian2");

        Hessian2ObjectInput objectInput = (Hessian2ObjectInput) hessian2Serialization.deserialize(url, byteArrayInputStream);
        Object object = objectInput.readObject();
        System.out.println(object);

    }

    @Test
    public void andTest() {
        byte b = 106;
        System.out.println(b & 0xff);
    }
}
