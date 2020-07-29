package com.dhy.designpatterns.IteratorPattern;

public interface MyCollection {
    void add(Object o);
    int remove(Object o);
    int size();
    MyIterator iterator();
}
