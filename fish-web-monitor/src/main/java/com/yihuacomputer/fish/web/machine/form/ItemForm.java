package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmVendor;

/**
 * @author YiHua
 *
 */
public class ItemForm {
	
	private String name;
	private String value;
	
	
	
	
	public ItemForm() {
		
	}
	/**
	 * @param name
	 * @param value
	 */
	public ItemForm(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	
	/**
	 * @param atmVendors
	 * @return
	 */
	public static List<ItemForm> toForms(List<IAtmVendor> atmVendors){
		List<ItemForm> forms = new ArrayList<ItemForm>();
		for(IAtmVendor vendor : atmVendors){
			ItemForm form = new ItemForm(vendor.getName(), vendor.getId()+"");
			forms.add(form);
		}
		return forms;
	}
	/**
	 * @param atmTypes
	 * @return
	 */
	public static List<ItemForm> toTypeForms(List<IAtmType> atmTypes){
		List<ItemForm> forms = new ArrayList<ItemForm>();
		for(IAtmType atmType : atmTypes){
			ItemForm form = new ItemForm(atmType.getName(), atmType.getId()+"");
			forms.add(form);
		}
		return forms;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
