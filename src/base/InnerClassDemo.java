package base;/**
 * Created by lvaolin on 17/10/17.
 */

/**
 * transient 关键字的用例
 *
 * @author lvaolin
 * @create 17/10/17 下午1:40
 */
public class InnerClassDemo {


    public static void main(String[] args) {

        //被static修饰的内部类可以直接作为一个普通类来使用，而不需实例一个外部类
        User user = new InnerClassDemo.User();

        //如果没有用static修饰InterClass，则只能按如下方式调用
        InnerClassDemo innerClassDemo = new InnerClassDemo();
        School school = innerClassDemo.new School();
    }





      static  class User{
            private int id;
            private transient String name;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
      }

      class School{

            private int id;
            private transient String name;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

}
