/**
 *
 */
package com.yihuacomputer.fish.version.notice;

import java.io.File;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.VersionCatalog;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.api.version.job.task.ITask;

/**
 * @author xuxigang
 *
 */
public class NoticeForm {
    private long taskId;

    private String patch;

    private String patchNo;

    private String prePatchNo;

    private String serverPath;

    private String localPath;

    private String fileName;

    //文件大小
    private String fileSize;

    // 文件的MD5校验码
    private String md5CheckNum;

    private boolean uncompress;

    private boolean eagerRestart;

    // 软件分类的版本号通过页面自定义
    private boolean customVersion;

    private boolean system;

    private String deployStartDate;

    private String deployEndDate;

    private String versionCatalog;

    private String ret;

    private long cancelPreVer ;

    private long deployScheduler ;
    
    private String execBefore;
    private String execAfter;

    public NoticeForm() {
    }

    public NoticeForm(ITask task) {
        IVersion version = task.getVersion();
        this.taskId = task.getId();
        IVersionType type = version.getVersionType();
        this.patch = type.getTypeName();
        this.patchNo = version.getVersionNo();
        this.prePatchNo = version.getDependVersion() == null ? null : version.getDependVersion().getVersionNo();
        this.serverPath = VersionCfg.getVersionDir() + File.separator + version.getVersionType().getTypeName();
        this.localPath = version.getVersionPath();
        this.fileName = version.getServerPath();
        this.md5CheckNum = version.getMd5CheckNum();
        this.uncompress = version.isUncompress();
        this.eagerRestart = type.isAutoDeploy();
        this.customVersion = type.isCustomVersion();
        this.system = type.isSystem();
        this.versionCatalog = type.getVersionCatalog() == null ? VersionCatalog.OTHER.name() : type.getVersionCatalog().name();
        this.deployStartDate = task.getDeployStartDate() == null ? "" : DateUtils.getDate(task.getDeployStartDate());
        this.deployEndDate = task.getDeployEndDate() == null ? "" : DateUtils.getDate(task.getDeployEndDate());
        this.fileSize = getVersionFileSize();
        this.execBefore = version.getExecBefore();
        this.execAfter = version.getExecAfter();
        this.cancelPreVer = task.getJob().getCancelPreVer() ;
//        this.deployScheduler = version.getDeployScheFlag();
    }

    private String getVersionFileSize(){
    	File file = new File(this.serverPath + File.separator + this.fileName);
    	if(file.exists()){
    		return String.valueOf(file.length());
    	}else{
    		return "0";
    	}
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public String getPatchNo() {
        return patchNo;
    }

    public void setPatchNo(String patchNo) {
        this.patchNo = patchNo;
    }

    public String getPrePatchNo() {
        return prePatchNo;
    }

    public void setPrePatchNo(String prePatchNo) {
        this.prePatchNo = prePatchNo;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public boolean isUncompress() {
        return uncompress;
    }

    public void setUncompress(boolean uncompress) {
        this.uncompress = uncompress;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public boolean isEagerRestart() {
        return eagerRestart;
    }

    public void setEagerRestart(boolean eagerRestart) {
        this.eagerRestart = eagerRestart;
    }

    public boolean isCustomVersion() {
        return customVersion;
    }

    public void setCustomVersion(boolean customVersion) {
        this.customVersion = customVersion;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public String getDeployStartDate() {
        return deployStartDate;
    }

    public void setDeployStartDate(String deployStartDate) {
        this.deployStartDate = deployStartDate;
    }

    public String getDeployEndDate() {
        return deployEndDate;
    }

    public void setDeployEndDate(String deployEndDate) {
        this.deployEndDate = deployEndDate;
    }

    public String getVersionCatalog() {
        return versionCatalog;
    }

    public void setVersionCatalog(String versionCatalog) {
        this.versionCatalog = versionCatalog;
    }

	public String getMd5CheckNum() {
		return md5CheckNum;
	}

	public void setMd5CheckNum(String md5CheckNum) {
		this.md5CheckNum = md5CheckNum;
	}

	public long getCancelPreVer() {
		return cancelPreVer;
	}

	public void setCancelPreVer(long cancelPreVer) {
		this.cancelPreVer = cancelPreVer;
	}

	public long getDeployScheduler() {
		return deployScheduler;
	}

	public void setDeployScheduler(long deployScheduler) {
		this.deployScheduler = deployScheduler;
	}

	public String getExecBefore() {
		return execBefore;
	}

	public void setExecBefore(String execBefore) {
		this.execBefore = execBefore;
	}

	public String getExecAfter() {
		return execAfter;
	}

	public void setExecAfter(String execAfter) {
		this.execAfter = execAfter;
	}

}
