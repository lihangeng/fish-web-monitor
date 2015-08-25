package com.yihuacomputer.fish.version.entity.deploy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DeployThreadService {

    private ThreadPoolExecutor deployExecutor;

    private int coreSize;

    public DeployThreadService(int coreSize) {
        this.coreSize = coreSize;
        this.init(coreSize);
    }

    public ThreadPoolExecutor getDeployExecutor() {
        return deployExecutor;
    }

    public int getCoreSize() {
        return coreSize;
    }

    /**
     * 初始化任务执行线程池
     * */

    public void init(int coreSize) {
        deployExecutor = new ThreadPoolExecutor(coreSize, coreSize, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(30), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void close() {
        if (deployExecutor != null) {
            deployExecutor.shutdownNow();
        }
    }

    /**
     * 执行任务
     * */
    public void execute(Runnable task) {
        deployExecutor.execute(task);
    }

}
