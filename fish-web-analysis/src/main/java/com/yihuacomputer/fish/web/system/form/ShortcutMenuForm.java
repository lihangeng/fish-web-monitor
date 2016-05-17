package com.yihuacomputer.fish.web.system.form;

public class ShortcutMenuForm {

	private String id;
	private String text;
	private boolean leaf = true;
	private boolean checked = false;
	private boolean expanded = false;

	public ShortcutMenuForm() {

	}
	
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

}
