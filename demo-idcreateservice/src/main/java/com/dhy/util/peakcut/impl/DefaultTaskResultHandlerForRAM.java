package com.dhy.util.peakcut.impl;

import com.dhy.util.peakcut.TaskRequestDto;
import com.dhy.util.peakcut.TaskResponseDto;
import com.dhy.util.peakcut.TaskStatus;
import com.dhy.util.peakcut.spi.TaskResultHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地内存作为结果存储（适合单机应用）
 */
public class DefaultTaskResultHandlerForRAM implements TaskResultHandler {

    private LocalRAMCache<TaskResponseDto> localRAMCache;

    public DefaultTaskResultHandlerForRAM(){
        this.localRAMCache = new LocalRAMCache<>();
    }

    @Override
    public void initResult(TaskRequestDto requestDto) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setTaskStatus(TaskStatus.status_queue);
        localRAMCache.put(requestDto.getTaskRequestId(),taskResponseDto);
    }

    @Override
    public void beforeResult(TaskRequestDto requestDto) {
        TaskResponseDto res = localRAMCache.get(requestDto.getTaskRequestId());
        if (res==null) {
            res = new TaskResponseDto();
        }
        res.setTaskStatus(TaskStatus.status_running);
        localRAMCache.put(requestDto.getTaskRequestId(),res);
    }

    @Override
    public void afterResult(TaskRequestDto requestDto, Object result) {
        TaskResponseDto res = localRAMCache.get(requestDto.getTaskRequestId());
        if (res==null) {
            res = new TaskResponseDto();
        }
        res.setTaskStatus(TaskStatus.status_completed);
        res.setTaskResult(result);
        localRAMCache.put(requestDto.getTaskRequestId(),res);
    }

    @Override
    public void exceptionResult(TaskRequestDto requestDto, Object result) {
        TaskResponseDto res = localRAMCache.get(requestDto.getTaskRequestId());
        if (res==null) {
            res = new TaskResponseDto();
        }
        res.setTaskStatus(TaskStatus.status_exception);
        if(result instanceof Exception){
            res.setTaskErrorMsg(((Exception) result).getMessage());
        }else{
            res.setTaskErrorMsg(result.toString());
        }
        localRAMCache.put(requestDto.getTaskRequestId(),res);

    }

    @Override
    public TaskResponseDto queryResult(String taskRequestId) {
        TaskResponseDto res = localRAMCache.get(taskRequestId);
        if (res==null) {
            res = new TaskResponseDto();
        }
        return res;
    }

    @Override
    public void removeResult(String taskRequestId) {
        localRAMCache.remove(taskRequestId);
    }


    public class LocalRAMCache<T> {
        private Map<String,T> cachemap = new ConcurrentHashMap<>();

        public  Map<String,T> getCachemap() {
            return cachemap;
        }

        public  T get(String key) {
            return cachemap.get(key);
        }

        public  void remove(String key) {
            cachemap.remove(key);
        }

        public  void put(String key, T object) {
            cachemap.put(key, object);
        }

    }
}
