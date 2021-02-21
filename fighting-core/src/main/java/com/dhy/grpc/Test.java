package com.dhy.grpc;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @Title Test
 * @Description
 * @Author lvaolin
 * @Date 2021/2/21 22:55
 **/
public class Test {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        PersonDto.Person.Builder builder = PersonDto.Person.newBuilder();
        builder.setId(1);
        builder.setName("laolv");
        builder.setEmail("laolv@qq.com");

        PersonDto.Person person = builder.build();
        System.out.println("before:" + person);

        System.out.println("===Person Byte:");
        for (byte b : person.toByteArray()) {
            System.out.print(b);
        }
        System.out.println();
        System.out.println("================");

        byte[] byteArray = person.toByteArray();
        PersonDto.Person p2 = PersonDto.Person.parseFrom(byteArray);
        System.out.println("after id:" + p2.getId());
        System.out.println("after name:" + p2.getName());
        System.out.println("after email:" + p2.getEmail());
    }
}
