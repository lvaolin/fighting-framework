package com.dhy.util.peakcut;

public enum TaskStatus {
    STATUS_WAITING ,    //等待返回
    STATUS_NO_REQUEST , //没有请求（或已经被清理）
    STATUS_RESPONSE ,     //已经返回
    STATUS_EXCEPTION      //返回异常 -- 类内部用，不返回给用户，直接抛出
}
