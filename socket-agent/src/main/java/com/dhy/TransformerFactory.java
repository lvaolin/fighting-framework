package com.dhy;

import com.dhy.handler.ITransformer;
import com.dhy.handler.OracleTcpNTAdapterTransformer;
import com.dhy.handler.SocketAdaptorTransformer;
import com.dhy.handler.SocketTransformer;

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
        map.put("java/net/Socket",new SocketTransformer());
        //map.put("sun/nio/ch/SocketAdaptor",new SocketAdaptorTransformer());
        map.put("oracle/net/nt/TcpNTAdapter",new OracleTcpNTAdapterTransformer());
    }
    public static byte[] getBytecode(String className){
        if (map.get(className)!=null) {
            return map.get(className).transform();
        }
        return null;
    }
}
