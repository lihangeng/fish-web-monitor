package com.yihuacomputer.fish.web.command.format;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件系统
 * @author YiHua
 *
 */
public class FileSystemForm {
	public String diskName;
	public String path;
	public String name;
	public FileType type;
	public String createdTime;
	public String lastTime;
	public long size;

	public FileSystemForm() {

	}

	public FileSystemForm(FileSystemForm fileSystemForm) {

		setName(fileSystemForm.getName());
		setDiskName(fileSystemForm.getDiskName());
		setPath(fileSystemForm.getPath());
		setSize(fileSystemForm.getSize());
		setCreateTime(fileSystemForm.getCreateTime());
		setLastTime(fileSystemForm.getLastTime());
		setType(fileSystemForm.getType());
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FileType getType() {
		return type;
	}

	public void setType(FileType type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createdTime;
	}

	public void setCreateTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public static List<FileSystemForm> convert(List<FileSystemForm> list) {
		List<FileSystemForm> result = new ArrayList<FileSystemForm>();
		for (FileSystemForm item : list) {
			result.add(new FileSystemForm(item));
		}
		return result;
	}

}
