package com.dhy.interrupted;

import lombok.Data;

import java.util.concurrent.*;

/**
 * 子线程执行任务 返回结果给 主线程
 */
public class Main4 {

    static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws InterruptedException{
        Future<TaskDto> future1 = executorService.submit(new Task1());
        try {
            TaskDto o = future1.get();
            System.out.println(o);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

    }



    static class Task1 implements Callable{
        @Override
        public Object call() throws Exception {
            System.out.println("任务1开始-----");
            TaskDto taskDto = new TaskDto();
            taskDto.setF1("1");
            taskDto.setF2("2");
            return taskDto;
        }
    }
    @Data
    static class TaskDto{
        private String f1;
        private String f2;
    }


}
