package base;/**
 * Created by lvaolin on 17/10/27.
 */

/**
 * 子类父类实例化过程
 *
 * @author lvaolin
 * @create 17/10/27 上午11:38
 */
public class B extends A{

    static {
        System.out.println("a");
    }

    public B(){
        System.out.println("b");
    }

    public static void main(String[] args) {
        A ab = new B(); //1a2b
        ab = new B(); //2b
    }

}
