package com.yihuacomputer.fish.web.system.form;

import com.yihuacomputer.fish.api.permission.IPermission;

/**
 * @author YiHua
 *
 */
public class MenuTreeForm {

	private String id;

	private String text;

	private String action;

	private boolean leaf = true;

	public MenuTreeForm() {

	}

	/**
	 * @param permission
	 */
	public MenuTreeForm(IPermission permission) {
		this.id = permission.getId();
		this.text = permission.getDescription();
		this.action = permission.getCode();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
