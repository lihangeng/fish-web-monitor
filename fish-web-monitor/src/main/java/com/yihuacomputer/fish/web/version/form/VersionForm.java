package com.yihuacomputer.fish.web.version.form;

import java.io.File;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.api.version.VersionStatus;

/**
 * @author YiHua
 *
 */
public class VersionForm {
	private long id;

	private String createdTime;

	private long versionTypeId;

	private String versionType;

	private long dependVersionId;

	private String dependVersion;

	private String versionNo;

	private String versionPath;

	private String serverPath;

	private boolean autoDown;

	private boolean uncompress;

	private boolean eagerRestart;

	private String desc;

	private String fullName;

	private String displayName;

	private String dvDisplayName;// 依赖版本的显示名称

	private VersionStatus versionStatus;
	
	private int downLoadCounter;

	private String userName;

	private String execBefore;

	private String execAfter;
	
	private String versionTypeDesc;

	public VersionForm() {
	}

	/**
	 * @param version
	 */
	@SuppressWarnings("deprecation")
	public VersionForm(IVersion version) {
		this.id = version.getId();
		this.versionNo = version.getVersionNo();
		this.versionPath = version.getVersionPath();
		this.versionType = version.getVersionType().getTypeName();
		this.versionTypeDesc = version.getVersionType().getDesc();
		this.versionTypeId = version.getVersionType().getId();
		this.createdTime = DateUtils.getTimestamp(version.getCreatedTime());
		this.serverPath = getVersionFile(versionType, version.getServerPath());
		this.autoDown = version.isAutoDown();
		this.desc = version.getDesc();
		this.fullName = version.getFullName();
		this.downLoadCounter = version.getDownloadCounter();
		this.displayName = this.fullName + " [" + this.serverPath + "]";
		if (version.getDependVersion() != null) {
			this.dependVersion = version.getDependVersion().getFullName();
			this.dependVersionId = version.getDependVersion().getId();
			this.dvDisplayName = version.getDependVersion().getFullName() + " [" + version.getDependVersion().getServerPath() + "]";
		}
		this.versionStatus = version.getVersionStatus();
		this.uncompress = version.isUncompress();
		this.eagerRestart = version.isEagerRestart();
		this.execBefore = version.getExecBefore();
		this.execAfter = version.getExecAfter();
	}

	private String getVersionFile(String typeName, String fileName) {
		File file = new File(VersionCfg.getVersionDir() + System.getProperty("file.separator") + typeName + File.separator + fileName);
		return file.exists() ? fileName : null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getVersionType() {
		return versionType;
	}

	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}

	public String getDependVersion() {
		return dependVersion;
	}

	public void setDependVersion(String dependVersion) {
		this.dependVersion = dependVersion;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getVersionPath() {
		return versionPath;
	}

	public void setVersionPath(String versionPath) {
		this.versionPath = versionPath;
	}

	public boolean isAutoDown() {
		return autoDown;
	}

	public void setAutoDown(boolean autoDown) {
		this.autoDown = autoDown;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getVersionTypeId() {
		return versionTypeId;
	}

	public void setVersionTypeId(long versionTypeId) {
		this.versionTypeId = versionTypeId;
	}

	public long getDependVersionId() {
		return dependVersionId;
	}

	public void setDependVersionId(long dependVersionId) {
		this.dependVersionId = dependVersionId;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public VersionStatus getVersionStatus() {
		return versionStatus;
	}

	public void setVersionStatus(VersionStatus versionStatus) {
		this.versionStatus = versionStatus;
	}

	public String getDvDisplayName() {
		return dvDisplayName;
	}

	public void setDvDisplayName(String dvDisplayName) {
		this.dvDisplayName = dvDisplayName;
	}

	public boolean isUncompress() {
		return uncompress;
	}

	public void setUncompress(boolean umcompress) {
		this.uncompress = umcompress;
	}

	public boolean isEagerRestart() {
		return eagerRestart;
	}

	public void setEagerRestart(boolean eagerRestart) {
		this.eagerRestart = eagerRestart;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExecBefore() {
		return execBefore;
	}

	public void setExecBefore(String execBefore) {
		this.execBefore = execBefore;
	}

	public String getVersionTypeDesc() {
		return versionTypeDesc;
	}

	public void setVersionTypeDesc(String versionTypeDesc) {
		this.versionTypeDesc = versionTypeDesc;
	}

	public int getDownLoadCounter() {
		return downLoadCounter;
	}

	public void setDownLoadCounter(int downLoadCounter) {
		this.downLoadCounter = downLoadCounter;
	}

	public String getExecAfter() {
		return execAfter;
	}

	public void setExecAfter(String execAfter) {
		this.execAfter = execAfter;
	}
	
}
