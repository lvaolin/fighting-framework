package com.dhy.dropframe;

/**
 *  测试idea工具的 drop frame 调试功能，呵呵
 * @author lvaolin
 * @create 2019/11/29 11:30 AM
 */
public class IdeaDropFrameTest {

    public static int g=0;
    public static void main(String[] args) {
        IdeaDropFrameTest test = new IdeaDropFrameTest();
        for (int i = 0; i < 10; i++) {
            test.method1(i);
        }
    }

    public void method1(int i){
        System.out.println("method1:"+i);
        method2(i);
    }
    public void method2(int j){
        j++;
        g++;
        System.out.println("method2:"+j);
    }
}

