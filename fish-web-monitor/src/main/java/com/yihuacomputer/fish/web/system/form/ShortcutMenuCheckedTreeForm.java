package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IPermission;

/**
 * @author YiHua
 *
 */
public class ShortcutMenuCheckedTreeForm {

	private String id;
	private String text;
	private boolean leaf = true;
	private boolean checked = false;
	private boolean expanded = false;

	public ShortcutMenuCheckedTreeForm() {

	}

	/**
	 * @param permission
	 * @param permissions
	 */
	public ShortcutMenuCheckedTreeForm(IPermission permission,List<IPermission> permissions) {
		id = permission.getCode();
		text = permission.getDescription();
		this.checked = permissions.contains(permission);
		if(this.checked){
			this.expanded = true;
		}
		leaf = !(this.hasSubMenu(permission));
		
		
	}
	/**
	 * 检查下面是否有子菜单   解决因页面上树节点因下面的子节点是button而现实出可以展开的问题  实际不能展开
	 * @param permission
	 * @return
	 */
	private boolean hasSubMenu(IPermission permission){
		for (IPermission item : permission.listChildren()) {
			if(!item.isButton()){
				return true;
			}
		}
		return false;
	}

	/**
	 * @param data
	 * @param permissions
	 * @return
	 */
	public static List<ShortcutMenuCheckedTreeForm> convert(Iterable<IPermission> data,List<IPermission> permissions) {
		List<ShortcutMenuCheckedTreeForm> result = new ArrayList<ShortcutMenuCheckedTreeForm>();
		for (IPermission item : data) {
			result.add(new ShortcutMenuCheckedTreeForm(item,permissions));
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
