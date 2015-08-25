package com.yihuacomputer.fish.web.system.form;

import com.yihuacomputer.fish.api.permission.IPermission;

/** 
 * @author 樊晓雨
 * @version
 * @date 2010-8-28
 */
public class Role2PermissionTreeForm {

	private String id;
	private boolean checked;
	private boolean disabled;
	private String text;
	private boolean leaf = true;
	
	public Role2PermissionTreeForm() {
		
	}
	
	public Role2PermissionTreeForm(IPermission permission, boolean checked,boolean disabled) {
		id = permission.getCode();
		this.checked = checked;
		this.disabled = disabled;
		text = permission.getDescription();
		leaf = !(permission.listChildren().iterator().hasNext());
	}
	
//	public static List<Role2PermissionTreeForm> convert(Iterable<IPermission> data, IRolePermissionRelation relation) {
//		List<Role2PermissionTreeForm> result = new ArrayList<Role2PermissionTreeForm>();
//		for(IPermission item : data) {
//			result.add(new Role2PermissionTreeForm(item));
//		}
//		return result;
//	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
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

}	
