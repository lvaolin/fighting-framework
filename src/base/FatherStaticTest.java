package base;/**
 * Created by lvaolin on 17/10/27.
 */

/**
 * 测试子类父类实例化过程
 *
 * @author lvaolin
 * @create 17/10/27 上午10:55
 */
public class FatherStaticTest {

    int nostaticvar=100;
    static int staticvar=101;
    static
    {
        System.out.println("执行父类的静态代码块。"+staticvar);
    }

    FatherStaticTest()
    {
        System.out.println("执行父类的不带参数的构造方法。"+nostaticvar);
    }

    FatherStaticTest(int num)
    {
        System.out.println("执行父类的带参数的构造方法。"+nostaticvar);
    }

    FatherStaticTest(String str)
    {
        System.out.println("执行父类的带参数的构造方法。"+nostaticvar);
    }

    {
        int i = 1;
        int j = 2;
        int sum = (i+j);
        System.out.println("执行父类的构造代码块。"+sum);
    }

    {
        int i = 1;
        int j = 2;
        int sum = (i+j);
        System.out.println("执行父类的构造代码块。"+sum);
    }

    {
        int m = 3;
        int n = 4;
        int sum = (m+n);
        System.out.println("执行父类的构造代码块。"+sum);
    }
}
