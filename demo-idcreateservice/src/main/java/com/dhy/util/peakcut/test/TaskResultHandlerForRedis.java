package com.dhy.util.peakcut.test;


import com.dhy.util.peakcut.TaskRequestDto;
import com.dhy.util.peakcut.TaskResponseDto;
import com.dhy.util.peakcut.spi.TaskResultHandler;
import org.springframework.stereotype.Component;

@Component
public class TaskResultHandlerForRedis implements TaskResultHandler {

    @Override
    public void initResult(TaskRequestDto requestDto) {

    }

    @Override
    public void beforeResult(TaskRequestDto requestDto) {

    }

    @Override
    public void afterResult(TaskRequestDto requestDto, Object result) {

    }

    @Override
    public void exceptionResult(TaskRequestDto requestDto, Object exception) {

    }

    @Override
    public TaskResponseDto queryResult(String taskRequestId) {
        return null;
    }

    @Override
    public void removeResult(String taskRequestId) {

    }
}
