package com.dhy.handler;

import javassist.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Project socket-agent
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/12/2 上午11:41
 */
public class SocketChannelImplTransformer implements ITransformer,IMonitor {

    @Override
    public void startMonitor() {

    }

    @Override
    public byte[] transform() {
        return new byte[0];
    }
}
