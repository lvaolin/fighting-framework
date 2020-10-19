package com.dhy.demo01_elasticjob.elasticjob;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyElasticJobConfig {

    private Logger logger = LoggerFactory.getLogger(MyElasticJobConfig.class);

    private int shardingTotalCount=2;
    private String serverLists = "127.0.0.1:2181";
    private String namespace = "elastic-job";
    @Autowired
    private Job1 job1;
    @Bean
    public ZookeeperConfiguration zkConfig() {
        logger.info("ZookeeperConfiguration:serverLists:" + serverLists);
        return new ZookeeperConfiguration(serverLists, namespace);
    }

    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter regCenter(ZookeeperConfiguration config) {
        return new ZookeeperRegistryCenter(config);
    }
    @Bean(initMethod = "init")
    public JobScheduler jobSchedulerFpzhfwptFpxz() {
        return new SpringJobScheduler(job1, regCenter(zkConfig()), fpzhfwptFpxz());
    }
    private LiteJobConfiguration fpzhfwptFpxz() {
        JobCoreConfiguration coreConfig = JobCoreConfiguration
                .newBuilder("发票下载", "0/30 * * * * ?", shardingTotalCount)
                .misfire(true)
                //.jobProperties("executor_service_handler","com.ttk.edf.agg.job.base.MyExecutorServiceHandler")
                .build();
        // 定义DATAFLOW类型配置
        DataflowJobConfiguration jobConfig = new DataflowJobConfiguration(coreConfig,
                Job1.class.getCanonicalName(), false);
        // 定义Lite作业根配置
        LiteJobConfiguration rootConfig = LiteJobConfiguration.newBuilder(jobConfig)
                .overwrite(true)
                //.monitorExecution(true)
                .monitorPort(9888)
                .build();
        return rootConfig;
    }



}
