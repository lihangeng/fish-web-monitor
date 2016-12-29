package com.yihuacomputer.fish.web.command.format;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统进程
 * 
 * @author wangchao
 * 
 */
public class ProcessForm {
	/**
	 * 程序名称
	 */
	private String name;
	/**
	 * 用户名
	 */
	private String user;
	/**
	 * cpu使用
	 */
	private double cpuRate;
	/**
	 * 内存使用
	 */
	private long memoryRate;
	/**
	 * 描述
	 */
	private String description;

	public ProcessForm() {

	}

	/**
	 * @param processForm
	 */
	public ProcessForm(ProcessForm processForm) {
		setName(processForm.getName());
		setUser(processForm.getUser());
		setCpuRate(processForm.getCpuRate());
		setMemoryRate(processForm.getMemoryRate());
		setDescription(processForm.getDescription());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public double getCpuRate() {
		return cpuRate;
	}

	public void setCpuRate(double cpuRate) {
		this.cpuRate = cpuRate;
	}

	public long getMemoryRate() {
		return memoryRate;
	}

	public void setMemoryRate(long memoryRate) {
		this.memoryRate = memoryRate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param list
	 * @return
	 */
	public static List<ProcessForm> convert(List<ProcessForm> list) {
		List<ProcessForm> result = new ArrayList<ProcessForm>();
		for (ProcessForm item : list) {
			result.add(new ProcessForm(item));
		}
		return result;
	}

}
