package com.dhy.springwebflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class HelloWebFlux {
    @Bean
    public RouterFunction<ServerResponse> hello(HelloHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        handler::hello);
    }

    @Bean
    public RouterFunction<ServerResponse> helloword(HelloHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/helloword")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        new HandlerFunction<ServerResponse>() {
                            @Override
                            public Mono<ServerResponse> handle(ServerRequest serverRequest) {
                                Mono<ServerResponse> body = ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                                        .body(BodyInserters.fromObject("Hello, webflux !"));

                                return body;
                            }
                        });
    }
}