package com.yihuacomputer.fish.web.index.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Menu {
	private String text;
	private String cls;
	private String action;
	private List<Menu> menu = new ArrayList<Menu>();

	public Menu(){}

	public Menu(String text,String cls,String action){
		this(text,cls);
		this.action = action;
	}

	public Menu(String text,String cls){
		this.text = text;
		this.cls = cls;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCls() {
		return this.cls;
	}

	public void addSubMenu(Menu menu){
		this.menu.add(menu);
	}

	public void addSubMenu(String text,String cls,String action){
		this.menu.add(new Menu(text,cls,action));
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public String toConfig(){
		if(StringUtils.isEmpty(this.getText())){
			return null;
		}
		StringBuffer hql = new StringBuffer();
		hql.append("{text:'").append(this.getText()).append("'");
		hql.append(",cls:'").append(this.getCls()).append("'");
		if(StringUtils.isNotEmpty(this.getAction())){
			hql.append(",action:'").append(this.getAction()).append("'");
		}
		if(!this.menu.isEmpty()){
			hql.append(",menu:{items:[");
			for(Menu each : menu){
				hql.append(each.toConfig()).append(",");
			}
			hql.deleteCharAt(hql.length()-1);
			hql.append("]}");
		}
		hql.append("}");
		return hql.toString();
	}

}
