package com.dhy.designpatterns.StatePattern2;

public interface CarState {

    void open();
    void close();
    void running();
    void stop();
}
