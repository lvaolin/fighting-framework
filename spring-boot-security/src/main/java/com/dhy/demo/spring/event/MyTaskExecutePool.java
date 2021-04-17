package com.dhy.demo.spring.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class MyTaskExecutePool {
	private static final Logger log = LoggerFactory.getLogger(MyTaskExecutePool.class);
	@Bean("myTaskAsyncPool")
	public Executor myTaskAsyncPool() {
		log.info("start TaskExecutePool myTaskAsyncPool");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);//配置核心线程数
		executor.setMaxPoolSize(20);//配置核心线程数
		executor.setQueueCapacity(1000);//配置队列容量
		executor.setKeepAliveSeconds(60);//设置线程活跃时间
		executor.setThreadNamePrefix("myTaskAsyn-");//设置线程名
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //设置拒绝策略
		executor.initialize();  
		return executor;  
	}  
}
