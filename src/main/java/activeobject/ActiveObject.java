package activeobject;/**
 * Created by lvaolin on 17/11/23.
 */

/**
 * @author lvaolin
 * @create 17/11/23 下午8:07
 */
public interface ActiveObject {


    public  abstract Result<String> makeString(int count,char  fillchar);
    public  abstract void  displayString(String  string);
}
