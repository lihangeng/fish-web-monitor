package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IPermission;

public class PermissionCheckedTreeForm {

	private String id;
	private String text;
	private boolean leaf = true;
	private boolean checked = false;
	private boolean expanded = false;

	public PermissionCheckedTreeForm() {

	}

	public  PermissionCheckedTreeForm(IPermission permission,List<IPermission> permissions) {
		if(permissions != null)
		{
		id = permission.getCode();
		text = permission.getDescription();
		this.checked = permissions.contains(permission);
//		if(this.checked){
			this.expanded = true;
//		}
		leaf = !(permission.listChildren().iterator().hasNext());
		}

	}

	public static List<PermissionCheckedTreeForm> convert(Iterable<IPermission> data,List<IPermission> permissions) {
		List<PermissionCheckedTreeForm> result = new ArrayList<PermissionCheckedTreeForm>();
		if(permissions != null)
		{
		for (IPermission item : data) {
			result.add(new PermissionCheckedTreeForm(item,permissions));
		}
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
