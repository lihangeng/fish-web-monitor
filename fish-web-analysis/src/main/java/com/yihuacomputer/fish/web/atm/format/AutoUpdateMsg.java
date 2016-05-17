package com.yihuacomputer.fish.web.atm.format;

import java.util.ArrayList;
import java.util.List;

/**
 * 软件版本自动更新信息
 * 
 * @author YiHua
 * 
 */
public class AutoUpdateMsg {
    private String termId;

    private List<SimpleVersion> currentPatches = new ArrayList<SimpleVersion>();

    private List<PatchMsg> autoUpdatePatches = new ArrayList<PatchMsg>();

    private List<TaskMsg> tasks = new ArrayList<TaskMsg>();

    private String ret;

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public List<SimpleVersion> getCurrentPatches() {
        return currentPatches;
    }

    public void setCurrentPatches(List<SimpleVersion> currentPatches) {
        this.currentPatches = currentPatches;
    }

    public List<PatchMsg> getAutoUpdatePatches() {
        return autoUpdatePatches;
    }

    public void setAutoUpdatePatches(List<PatchMsg> autoUpdatePatches) {
        this.autoUpdatePatches = autoUpdatePatches;
    }

    public void addAutoUpdatePatch(PatchMsg patch) {
        this.autoUpdatePatches.add(patch);
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public void addTask(TaskMsg task) {
        tasks.add(task);
    }

    public void removeTask(TaskMsg task) {
        tasks.remove(task);
    }

    public void removeTask(String downTypeName, String downVersionNo) {
        TaskMsg task = findTask(downTypeName, downVersionNo);
        if (task != null) {
            removeTask(task);
        }
    }

    public TaskMsg findTask(String downTypeName, String downVersionNo) {
        for (TaskMsg task : tasks) {
            if (task.getDownTypeName().equalsIgnoreCase(downTypeName) && task.getDownVersionNo().equalsIgnoreCase(downVersionNo)) {
                return task;
            }
        }
        return null;
    }

    public List<TaskMsg> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskMsg> tasks) {
        this.tasks = tasks;
    }

}
