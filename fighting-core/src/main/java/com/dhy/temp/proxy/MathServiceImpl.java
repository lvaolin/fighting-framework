package com.dhy.temp.proxy;

public class MathServiceImpl implements IMathService {
    @Override
    public int add(int x, int y) {
        return x+y;
    }
}
