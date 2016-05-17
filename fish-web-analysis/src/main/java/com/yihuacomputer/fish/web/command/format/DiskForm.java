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

	public String label;
	public String name;
	public String labelAndname;
	public long totalSize;
	public long freeSize;
	public String fileSys;
	public String memo;
	private String path;

	public DiskForm() {

	}

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
		this.path = name.substring(0, end+1);
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

	public static List<DiskForm> convert(List<DiskForm> list) {
		List<DiskForm> result = new ArrayList<DiskForm>();
		for (DiskForm item : list) {
			result.add(new DiskForm(item));
		}
		return result;
	}

}
