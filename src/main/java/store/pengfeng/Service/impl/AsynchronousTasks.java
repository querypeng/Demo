package store.pengfeng.Service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author pengfeng
 */
@Component
public class AsynchronousTasks {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * customThreadPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
     * @param i
     */
    @Async("customThreadPool")
    public void doTask(int i) {
        logger.error("Task" + i + " started.");
    }
}
