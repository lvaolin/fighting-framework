package aop.hello;

/**
 * @author lvaolin
 * @create 2019/7/15 6:32 PM
 */
public class HelloImpl implements IHello{
    @Override
    public void sayHello(String name) {
        System.out.println("hello......."+name);
    }

    @Override
    public void sayOk(String name) {

    }

    @Override
    public void sayMorning(String name) {

    }
}
