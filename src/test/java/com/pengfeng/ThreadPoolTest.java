package com.pengfeng;

import org.junit.Test;
import store.pengfeng.Service.impl.AsynchronousTasks;

import javax.annotation.Resource;

public class ThreadPoolTest {

    @Resource
    private AsynchronousTasks asynchronousTasks;

    @Test
    public void fn1(){
        for (int i = 0; i < 100; i++) {
            asynchronousTasks.doTask(i);
        }
        System.err.println("All tasks finished.");
    }

}
