package com.yihuacomputer.fish.web.daily.form;

import java.lang.Thread.State;

public class ServiceThreadForm {

    /**
     * 每日备份主线程状态
     */
    private State daybackThreadState;

    /**
     * 失败任务备份主线程状态
     */
    private State redoBackTreadState;

    /**
     * 并发执行任务数
     */
    private int ActiveRuners;

    /**
     * 版本下发作业状态
     */
    public State jobManagerState;

    /**
     * 版本下发任务执行状态
     */
    public State taskMangerState;

    /**
     * 最大作业队列数
     */
    public int maxJobCount;

    /**
     * 队列中作业数
     */
    public int jobQueueCount;

    /**
     * 正在执行任务数
     */
    public int activeTaskCount;

    public State getDaybackThreadState() {
        return daybackThreadState;
    }

    public void setDaybackThreadState(State daybackThreadState) {
        this.daybackThreadState = daybackThreadState;
    }

    public State getRedoBackTreadState() {
        return redoBackTreadState;
    }

    public void setRedoBackTreadState(State redoBackTreadState) {
        this.redoBackTreadState = redoBackTreadState;
    }

    public int getActiveRuners() {
        return ActiveRuners;
    }

    public void setActiveRuners(int activeRuners) {
        ActiveRuners = activeRuners;
    }

    public State getJobManagerState() {
        return jobManagerState;
    }

    public void setJobManagerState(State jobManagerState) {
        this.jobManagerState = jobManagerState;
    }

    public State getTaskMangerState() {
        return taskMangerState;
    }

    public void setTaskMangerState(State taskMangerState) {
        this.taskMangerState = taskMangerState;
    }

    public int getMaxJobCount() {
        return maxJobCount;
    }

    public void setMaxJobCount(int maxJobCount) {
        this.maxJobCount = maxJobCount;
    }

    public int getJobQueueCount() {
        return jobQueueCount;
    }

    public void setJobQueueCount(int jobQueueCount) {
        this.jobQueueCount = jobQueueCount;
    }

    public int getActiveTaskCount() {
        return activeTaskCount;
    }

    public void setActiveTaskCount(int activeTaskCount) {
        this.activeTaskCount = activeTaskCount;
    }

}
