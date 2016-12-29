package com.yihuacomputer.fish.web.command.format;

import java.util.ArrayList;
import java.util.List;

/**
 * 磁盘实体类：
 * 
 * @author huxiaobao
 * 
 */
public class DiskForm {

	/**
	 * 标签
	 */
	public String label;
	/**
	 * 名称
	 */
	public String name;
	/**
	 * 标签名称
	 */
	public String labelAndname;
	/**
	 * 磁盘大小
	 */
	public long totalSize;
	/**
	 * 可用大小
	 */
	public long freeSize;
	/**
	 * 文件系统
	 */
	public String fileSys;
	/**
	 * 内存
	 */
	public String memo;
	/**
	 * 路径
	 */
	private String path;

	public DiskForm() {

	}

	/**
	 * @param diskForm
	 */
	public DiskForm(DiskForm diskForm) {

		setFileSys(diskForm.getFileSys());
		setFreeSize(diskForm.getFreeSize());
		setLabel(diskForm.getLabel());
		setLabelAndname(diskForm.getLabelAndname());
		setMemo(diskForm.getMemo());
		setName(diskForm.getName());
		setTotalSize(diskForm.getTotalSize());
		setPath(diskForm.getName());
	}

	public String getPath() {
		return path;
	}

	public void setPath(String name) {
		int end = name.lastIndexOf(":");
		//适配liunx系统以/开始
		if(name.startsWith("/")){
			this.path = name;
		}
		else{
			this.path = name.substring(0, end+1);
		}
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public void setFreeSize(long freeSize) {
		this.freeSize = freeSize;
	}

	public void setFileSys(String fileSys) {
		this.fileSys = fileSys;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setLabelAndname(String labelAndname) {
		this.labelAndname = labelAndname;
	}

	public String getLabel() {
		return this.label;
	}

	public String getName() {
		return this.name;
	}

	public long getTotalSize() {
		return this.totalSize;
	}

	public long getFreeSize() {
		return this.freeSize;
	}

	public String getFileSys() {
		return this.fileSys;
	}

	public String getMemo() {
		return this.memo;
	}

	public String getLabelAndname() {
		return this.labelAndname;
	}

	/**
	 * @param list
	 * @return
	 */
	public static List<DiskForm> convert(List<DiskForm> list) {
		List<DiskForm> result = new ArrayList<DiskForm>();
		for (DiskForm item : list) {
			result.add(new DiskForm(item));
		}
		return result;
	}

}
