package com.dhy.designpatterns.IteratorPattern;

public class MyList implements  MyCollection{
    @Override
    public void add(Object o) {

    }

    @Override
    public int remove(Object o) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public MyIterator iterator() {
        return new MyListIterator();
    }

    class MyListIterator implements MyIterator{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    }
}
