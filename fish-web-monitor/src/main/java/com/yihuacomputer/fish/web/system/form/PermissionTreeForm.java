package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IPermission;

/**
 * @author 徐西刚
 * @version
 * @date 2011-10-18
 */
public class PermissionTreeForm {

	private String id;
	private String text;
	private String desc;
	private boolean leaf = true;

	public PermissionTreeForm() {

	}

	/**
	 * @param permission
	 */
	public PermissionTreeForm(IPermission permission) {
		id = permission.getCode();
		text = permission.getDescription();
		desc = "备注";
		leaf = !(permission.listChildren().iterator().hasNext());
	}

	/**
	 * @param data
	 * @return
	 */
	public static List<PermissionTreeForm> convert(Iterable<IPermission> data) {
		List<PermissionTreeForm> result = new ArrayList<PermissionTreeForm>();
		for (IPermission item : data) {
			result.add(new PermissionTreeForm(item));
		}
		return result;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
