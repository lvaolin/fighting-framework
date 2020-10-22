package com.dhy.util.peakcut.test;


import com.dhy.util.peakcut.TaskRequestDto;
import com.dhy.util.peakcut.spi.TaskResultHandler;
import org.springframework.stereotype.Component;

@Component
public class TaskResultHandlerForRedis implements TaskResultHandler {
    @Override
    public Object initResult(TaskRequestDto requestDto) {
        return null;
    }

    @Override
    public Object beforeResult(TaskRequestDto requestDto) {
        return null;
    }

    @Override
    public Object afterResult(TaskRequestDto requestDto, Object result) {
        return null;
    }

    @Override
    public Object exceptionResult(TaskRequestDto requestDto, Object exception) {
        return null;
    }

    @Override
    public Object queryResult(String taskRequestId) {
        return null;
    }

    @Override
    public void removeResult(String taskRequestId) {

    }
}
