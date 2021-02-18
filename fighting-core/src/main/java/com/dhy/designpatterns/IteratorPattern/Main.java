package com.dhy.designpatterns.IteratorPattern;

/**
 * 迭代器模式核心思路：
 * 1、定义容器接口MyCollection，里面除了容器本身的增删方法外，还有一个获取迭代器的方法 iterator()
 * 2、定义一个迭代器接口MyIterator，里面至少有  hasNext()和 next() 方法
 * 3、定义容器实现类 MyList
 * 4、在容器实现类内部定义 迭代器实现类（内部类） MyListIterator 实现 MyIterator 接口
 * 5、
 */
public class Main {

    public static void main(String[] args) {

        MyCollection myList = new MyList();
        myList.add("a");
        myList.add("b");
        MyIterator iterator = myList.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }
    }
}
