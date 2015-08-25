package com.yihuacomputer.fish.web.command.format;

import java.util.List;

/**
 * 远程浏览文件系统的实体类.
 * 
 * @author huxiaobao
 * 
 */
public class ExplorerForm {
	private List<FileSystemForm> fileSystemList;
	private String diskName;
	private String path;
	private int offset;
	private int limit;
	private AgentRet ret;

	public String getDiskName() {
		return diskName;
	}

	public void setDiskName(String diskName) {
		this.diskName = diskName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<FileSystemForm> getFileSystemList() {
		return fileSystemList;
	}

	public void setFileSystemList(List<FileSystemForm> fileSystemList) {
		this.fileSystemList = fileSystemList;
	}

    public AgentRet getRet() {
        return ret;
    }

    public void setRet(AgentRet ret) {
        this.ret = ret;
    }

}
