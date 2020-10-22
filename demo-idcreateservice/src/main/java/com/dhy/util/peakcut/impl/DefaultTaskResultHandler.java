package com.dhy.util.peakcut.impl;

import com.dhy.util.peakcut.TaskRequestDto;
import com.dhy.util.peakcut.spi.TaskResultHandler;

public class DefaultTaskResultHandler implements TaskResultHandler {


    @Override
    public Object initResult(TaskRequestDto requestDto) {
        //redis 结果 初始化，状态置为 排队中
        return null;
    }

    @Override
    public Object beforeResult(TaskRequestDto requestDto) {
        //redis 结果 状态置为 处理中
        return null;
    }

    @Override
    public Object afterResult(TaskRequestDto requestDto, Object result) {
        //redis 结果 状态置为 完成
        return null;
    }

    @Override
    public Object exceptionResult(TaskRequestDto requestDto, Object result) {
        //redis 结果 状态置为 异常
        return null;
    }

    @Override
    public Object queryResult(String taskRequestId) {
        //从redis 查询结果
        return null;
    }

    @Override
    public void removeResult(String taskRequestId) {
        //redis存储 过期 自动失效 无需处理
    }
}
