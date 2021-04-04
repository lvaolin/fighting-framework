package com.dhy.demo01_elasticjob.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dhy.demo01_elasticjob.annotation.MyElasticJob;

@MyElasticJob(name = "JobTest",cron = "0/10 * * * * ?")
public class JobWithAnnotation implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("annotation simple job---");
    }
}
