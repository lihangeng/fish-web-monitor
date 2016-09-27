package com.yihuacomputer.fish.web.atm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.TaskCanceledException;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.api.version.job.task.TaskType;
import com.yihuacomputer.fish.web.atm.format.UpdateReportMsg;

/**
 *
 * 获取当前版本下载百分比
 *
 * @author pengwenchao
 * */
@Controller
@RequestMapping("/msg/updateversionstatus")
public class DownloadVersionStatusController {

    private Logger logger = LoggerFactory.getLogger(DownloadVersionStatusController.class);

    @Autowired
    private IVersionService versionService;

    /**
     * 接收版本下载时的百分比
     *
     * @param msg
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody UpdateReportMsg reciveMsg(@RequestBody UpdateReportMsg msg) {
        if (msg.getTaskId() > 0) {
            try {
                ITask task = versionService.collectUpdateReport(msg.getTaskId(), msg.getRet(),
                        msg.getDownloadingSize(), msg.getDownloadStartTime(), msg.getDownloadFinishTime());

                if (!((TaskType.AUTO_UPDATE.equals(task.getTaskType()) && TaskStatus.NEW.equals(task.getStatus()))
                        || (TaskType.MANUAL.equals(task.getTaskType()) && TaskStatus.NOTICED.equals(task.getStatus())) || TaskStatus.DOWNLOADING
                            .equals(task.getStatus()))) {

                    // 已经下载成功
                    msg.setRet("00");
                }
                // 正在下载
                msg.setRet("01");
            }
            catch (TaskCanceledException cancelException) {
                msg.setRet("CANCEL");
                logger.error(String.format("更新报告被取消:[%s]", JsonUtils.toJson(msg)));
            }
            catch (Exception e) {
                logger.error(String.format("更新报告信息异常![%s],异常内容:[%s]", e, JsonUtils.toJson(msg)));
            }
        }
        return msg;
    }
}
