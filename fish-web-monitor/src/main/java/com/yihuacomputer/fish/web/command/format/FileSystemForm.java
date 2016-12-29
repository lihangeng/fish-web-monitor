package com.yihuacomputer.fish.web.command.format;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件系统
 * @author YiHua
 *
 */
public class FileSystemForm {
	/**
	 * 磁盘名称
	 */
	public String diskName;
	/**
	 * 路径
	 */
	public String path;
	/**
	 * 名称
	 */
	public String name;
	/**
	 * 类型
	 */
	public FileType type;
	/**
	 * 创建时间
	 */
	public String createdTime;
	/**
	 * 最后修改时间
	 */
	public String lastTime;
	/**
	 * 大小
	 */
	public long size;

	public FileSystemForm() {

	}

	/**
	 * @param fileSystemForm
	 */
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

	/**
	 * @param list
	 * @return
	 */
	public static List<FileSystemForm> convert(List<FileSystemForm> list) {
		List<FileSystemForm> result = new ArrayList<FileSystemForm>();
		for (FileSystemForm item : list) {
			result.add(new FileSystemForm(item));
		}
		return result;
	}

}
