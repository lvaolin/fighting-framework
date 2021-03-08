package com.dhy.springboot.grpc.client.service;

import com.dhy.springboot.grpc.common.HelloReply;
import com.dhy.springboot.grpc.common.HelloRequest;
import com.dhy.springboot.grpc.common.SimpleGrpc.SimpleBlockingStub;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @Auther:lvaolin
 * @Date: 2021/3/8 18:47
 */
@Service
public class GrpcClientService {

    @GrpcClient("spring-boot-grpc-server")
    private SimpleBlockingStub simpleBlockingStub;

    public String sendMessage(String name) {
        try {
            HelloReply response = simpleBlockingStub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode();
        }
    }
}
