package com.yihuacomputer.fish.web.atm.format;

import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.version.notice.NoticeForm;

/**
 *
 * @since 0.17 修改成继承NoticeForm，因为自动更新也放到了任务列表中
 * @author xuxigang
 *
 */
public class PatchMsg extends NoticeForm{

    public PatchMsg(){}

    public PatchMsg(ITask task) {
      super(task);
    }
}
