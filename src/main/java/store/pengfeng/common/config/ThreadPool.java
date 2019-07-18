package store.pengfeng.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author pengfeng
 */
@Configuration
@EnableAsync
public class ThreadPool {

    @Value("${task.pool.corePoolSize}")
    private int corePoolSize;

    @Value("${task.pool.maxPoolSize}")
    private int maxPoolSize;

    @Value("${task.pool.keepAliveSeconds}")
    private int keepAliveSeconds;

    @Value("${task.pool.queueCapacity}")
    private int queueCapacity;

    @Bean
    public Executor customThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(corePoolSize);
        //保持线程活跃时间
        executor.setKeepAliveSeconds(keepAliveSeconds);
        //最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //队列容量
        executor.setQueueCapacity(queueCapacity);
        //线程名字前缀
        executor.setThreadNamePrefix("angel-");
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
