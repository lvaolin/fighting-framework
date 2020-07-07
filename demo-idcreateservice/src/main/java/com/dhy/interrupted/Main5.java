package com.dhy.interrupted;

import lombok.Data;

import java.util.concurrent.*;

/**
 * FutureTask 应用
 */
public class Main5 {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask(new Callable() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return "ok";
            }
        });
        futureTask.run();
        String s = futureTask.get();
        System.out.println(s);

    }



    static class Task1 implements Callable{
        @Override
        public TaskDto call() throws Exception {
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
