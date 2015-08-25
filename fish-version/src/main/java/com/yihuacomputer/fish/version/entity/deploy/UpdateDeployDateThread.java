package com.yihuacomputer.fish.version.entity.deploy;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistory;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistoryService;
import com.yihuacomputer.fish.api.version.job.NoticeStatus;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.version.notice.NoticeForm;

public class UpdateDeployDateThread implements Runnable {

    private Logger logger = LoggerFactory.getLogger(UpdateDeployDateThread.class);

    private ITask task;

    private IUpdateDeployDateHistoryService deployService;

    private Date deployStartDate;

    private final String path = "/ctr/patch/updateDeployDate";

    public UpdateDeployDateThread(ITask task, IUpdateDeployDateHistoryService deployService, Date deployStartDate) {
        this.task = task;
        this.deployService = deployService;
        this.deployStartDate = deployStartDate;
    }

    public ITask getTask() {
        return this.task;
    }

    public IUpdateDeployDateHistoryService getDeployService() {
        return deployService;
    }

    public Date getDeployStartDate() {
        return deployStartDate;
    }

    public String getPath() {
        return path;
    }

    @Override
    public void run() {
        IUpdateDeployDateHistory updateDeployDate = deployService.make(task);
        updateDeployDate.setDeployStartDate(deployStartDate);
        updateDeployDate.setNoticeTime(new Date());
        if (task.isDeploySuccess()) {
            updateDeployDate.setNoticeStatus(NoticeStatus.IGNORE);
        }
        deployService.add(updateDeployDate);

     // 通知
        try {
            IDevice device = task.getDevice();
            NoticeForm notice = new NoticeForm(task);
            HttpProxy.httpPost(getUrl(device.getIp()), notice, NoticeForm.class);
            updateDeployDate.setNoticeStatus(NoticeStatus.SUCCESS);
        } catch (Exception ex) {
            ex.printStackTrace();
            updateDeployDate.setNoticeStatus(NoticeStatus.FAIL);
            updateDeployDate.setReason("通知失败");
        }
        deployService.update(updateDeployDate);
    }

    private String getUrl(ITypeIP ip) {
        String url = MonitorCfg.getHttpUrl(ip.toString()) + path;
        logger.info(url);
        return url;
    }
}
