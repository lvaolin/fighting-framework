package com.dhy.util.peakcut;

import com.dhy.util.peakcut.impl.DefaultTaskResultHandlerForRAM;
import com.dhy.util.peakcut.internal.TaskThreadPoolManager;
import com.dhy.util.peakcut.spi.TaskResultHandler;
import java.util.UUID;

/**
 * 调用者入口 API
 */
public class TaskFacade {
    private  TaskResultHandler taskResultHandler ;
    public TaskFacade(){
        this.taskResultHandler = new DefaultTaskResultHandlerForRAM();
    }

    public TaskFacade(TaskResultHandler taskResultHandler){
        this.taskResultHandler = taskResultHandler;
    }

    /**
     * 提交任务
     * @param taskRequestDto
     * @return
     */
    public  String createTask(TaskRequestDto taskRequestDto){
        taskRequestDto.setTaskRequestId(UUID.randomUUID().toString());
        //初始化结果为 排队中
        taskResultHandler.initResult(taskRequestDto);
        TaskThreadPoolManager
                .getExecutorService(taskRequestDto.getTaskPoolKey())//获取专用线程池
                .submit(()->{
                    try {
                        taskResultHandler.beforeResult(taskRequestDto);
                        //执行任务
                        Object result = taskRequestDto.getCallable().call();
                        //保存结果
                        taskResultHandler.afterResult(taskRequestDto,result);

                    } catch (Exception e) {
                        e.printStackTrace();
                        taskResultHandler.exceptionResult(taskRequestDto,e);
                    }
                });

        return  taskRequestDto.getTaskRequestId();
    }

    /**
     * 查询任务结果
     * 考虑各种场景：
     *  1）有请求，但是还没有返回 -- 返回waiting，保留该条记录，前端继续轮询等待
     *  2）请求已经返回，有正常返回值 -- 返回response（value），删除该条记录，前端结束轮询
     *  3）请求已经返回，接口内报错BusinessException -- 返回response（error），删除该条记录，前端结束轮询
     *  4）该请求不存在 -- 返回no request，前端结束轮询，提示错误
     *  5）请求发起后，长时间没有被取走结果 -- 定时清理该类记录，前端应该已经异常或退出 -- 通过Redis可以不用
     * @param taskRequestId
     * @return
     */
    public  TaskResponseDto queryTaskResult(String taskRequestId ){
        return  (TaskResponseDto)taskResultHandler.queryResult(taskRequestId);
    }


    public TaskResultHandler getTaskResultHandler() {
        return taskResultHandler;
    }

    public void setTaskResultHandler(TaskResultHandler taskResultHandler) {
        this.taskResultHandler = taskResultHandler;
    }
}
