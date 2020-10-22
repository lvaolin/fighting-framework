package com.dhy.util.peakcut.test;

import com.dhy.util.peakcut.TaskFacade;
import com.dhy.util.peakcut.spi.TaskResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TaskFacadeForConnector extends TaskFacade {
    @Autowired
    TaskResultHandler myTaskResultHandlerForRedis;
    @PostConstruct
    public void init(){
        super.setTaskResultHandler(this.myTaskResultHandlerForRedis);
    }
}
