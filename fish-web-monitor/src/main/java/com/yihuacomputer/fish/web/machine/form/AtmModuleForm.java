package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmModule;

public class AtmModuleForm {
	private long id;
	private String no;
	private String name;
	private String note;
	
	public AtmModuleForm(){
		
	}
	
	public AtmModuleForm(IAtmModule module){
		setId(module.getId());
		setName(module.getName());
		setNote(module.getNote());
		setNo(module.getNo());
	}
	
	public void translate(IAtmModule module){
		module.setId(getId());
		module.setName(getName());
		module.setNo(getNo());
		module.setNote(getNote());
	}
	
	public static List<AtmModuleForm> convert(List<IAtmModule> list) {
		List<AtmModuleForm> result = new ArrayList<AtmModuleForm>();
		for(IAtmModule item : list) {
			result.add(new AtmModuleForm(item));
		}
		return result;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
