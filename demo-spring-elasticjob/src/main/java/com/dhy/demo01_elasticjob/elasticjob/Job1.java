package com.dhy.demo01_elasticjob.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class Job1 implements DataflowJob {
    @Override
    public List fetchData(ShardingContext shardingContext) {
        System.out.println("fetchData--完毕---"+shardingContext.getShardingItem());
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        return arrayList;
    }

    @Override
    public void processData(ShardingContext shardingContext, List data) {
        if(shardingContext.getShardingItem()==0){
            try {
                TimeUnit.SECONDS.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("processData--完毕---"+shardingContext.getShardingItem());
    }
}
