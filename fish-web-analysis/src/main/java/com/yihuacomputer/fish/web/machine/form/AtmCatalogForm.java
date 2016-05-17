package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmCatalog;

public class AtmCatalogForm {
	private long id;
	private String no;
	private String name;
	private String note;

	public AtmCatalogForm(){
		
	}
	
	public AtmCatalogForm(IAtmCatalog catalog){
		setId(catalog.getId());
		setName(catalog.getName());
		setNo(catalog.getNo());
		setNote(catalog.getNote());
	}
	
	public static List<AtmCatalogForm> convert(List<IAtmCatalog> list) {
		List<AtmCatalogForm> result = new ArrayList<AtmCatalogForm>();
		for(IAtmCatalog item : list) {
			result.add(new AtmCatalogForm(item));
		}
		return result;
	}
	
    public void translate(IAtmCatalog catalog) {
    	catalog.setId(getId());
    	catalog.setName(getName());
    	catalog.setNo(getNo());
    	catalog.setNote(getNote());
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
