package com.dhy.patch.test;

/**
 * @Project mlife-tools
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/12/9 下午4:41
 */
public class Patch {
    private String name;

    public void m1() {
        System.out.println("m1" + name);
    }

    static class Woo2 {
        private String id;

        public void w1() {
            System.out.println("w1" + id);
        }
    }
}
