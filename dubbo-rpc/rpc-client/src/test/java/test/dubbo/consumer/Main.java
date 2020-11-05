package test.dubbo.consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.dubbo.common.TestUtil;
import test.dubbo.provider.IHelloService;

/**
 * @author lvaolin
 * @create 2020/7/23 10:55 AM
 */
@DisplayName("服务单元测试")
public class Main {

    @Test
    @DisplayName("hello")
    public void sayHello() {
        for (int i = 0; i < 100; i++) {
            System.out.println(TestUtil.getService(IHelloService.class).sayHello("lval"));
        }
//        synchronized (Main.class) {
//            try {
//                Main.class.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }


}
