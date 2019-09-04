package base;/**
 * Created by lvaolin on 17/10/27.
 */

/**
 * 子类实例化过程测试
 *
 * @author lvaolin
 * @create 17/10/27 上午10:55
 */
public class SonStaticTest  extends  FatherStaticTest{

    int nostaticvar=200;
    static int staticvar=201;
    static int sonstaticvar=202;
    static
    {
        System.out.println("执行子类的静态代码块。"+staticvar);
    }

    SonStaticTest()
    {

        System.out.println("执行子类的不带参数的构造方法。"+nostaticvar);
    }

    SonStaticTest(int num)
    {
        super(7);
        System.out.println("执行子类的带参数的构造方法。"+nostaticvar);
    }

    SonStaticTest(String str)
    {
        super(7);
        System.out.println("执行子类的带参数的构造方法。"+nostaticvar);
    }

    {
        int i = 1;
        int j = 2;
        int sum = (i+j);
        System.out.println("执行子类的构造代码块。"+sum);
    }

    {
        int i = 1;
        int j = 2;
        int sum = (i+j);
        System.out.println("执行子类的构造代码块。"+sum);
    }

    {
        int m = 3;
        int n = 4;
        int sum = (m+n);
        System.out.println("执行子类的构造代码块。"+sum);
    }


    public static void main(String[] args)
    {
        FatherStaticTest fatherStaticTest = new SonStaticTest();
        System.out.println(fatherStaticTest.nostaticvar);
        System.out.println(fatherStaticTest.staticvar);
        SonStaticTest sonStaticTest = new SonStaticTest();
        System.out.println(sonStaticTest.nostaticvar);
        System.out.println(sonStaticTest.staticvar);

    }
}
