package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmGroup;

public class AtmGroupForm {
	private long id;
	private String name;
	private String note;

	public AtmGroupForm(){
		
	}
	
	public AtmGroupForm(IAtmGroup atmGroup){
		setId(atmGroup.getId());
		setName(atmGroup.getName());
		setNote(atmGroup.getNote());
	}
	
	public static List<AtmGroupForm> convert(List<IAtmGroup> list) {
		List<AtmGroupForm> result = new ArrayList<AtmGroupForm>();
		for(IAtmGroup item : list) {
			result.add(new AtmGroupForm(item));
		}
		return result;
	}
	
    public void translate(IAtmGroup atmGroup) {
    	atmGroup.setId(getId());
    	atmGroup.setName(getName());
    	atmGroup.setNote(getNote());
    }
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
