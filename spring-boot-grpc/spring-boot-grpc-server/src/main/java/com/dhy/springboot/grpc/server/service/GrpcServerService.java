package com.dhy.springboot.grpc.server.service;

import com.dhy.springboot.grpc.common.HelloReply;
import com.dhy.springboot.grpc.common.HelloRequest;
import com.dhy.springboot.grpc.common.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @Auther:lvaolin
 * @Date: 2021/3/8 18:47
 */
@GrpcService
public class GrpcServerService extends SimpleGrpc.SimpleImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        System.out.println("GrpcServerService...");
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}