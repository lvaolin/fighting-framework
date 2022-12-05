package com.dhy.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Project socket-agent
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/12/2 上午11:36
 */
public class TransformerFactory {
    private static Map<String, ITransformer> map = new ConcurrentHashMap<>();
    static {
        map.put("java/net/Socket",new com.dhy.handler.SocketTransformer());
        //map.put("sun/nio/ch/SocketAdaptor",new SocketAdaptorTransformer());
        map.put("oracle/net/nt/TcpNTAdapter",new com.dhy.handler.OracleTcpNTAdapterTransformer());
    }
    public static byte[] getBytecode(String className){
        if (map.get(className)!=null) {
            return map.get(className).transform();
        }
        return null;
    }
    public static boolean isNeedTransformer(String className){
        return map.containsKey(className);
    }
}
