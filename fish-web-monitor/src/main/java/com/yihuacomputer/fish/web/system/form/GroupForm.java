package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.permission.IGroupService;
import com.yihuacomputer.fish.api.person.IOrganizationService;

/**
 * @author 樊晓雨
 * @version
 * @date 2010-8-19
 */
public class GroupForm {

	private long id;
	private String name;
	private String orgId;
	private String organization;
	private String organizationCode;

	public GroupForm() {
	}

	/**
	 * 
	 * @param group
	 */
	public GroupForm(IGroup group) {
		id = group.getId();
		name = group.getName();
		if (group.getOrganization() != null) {
			this.orgId = group.getOrganization().getGuid();
			organizationCode = group.getOrganization().getCode();
			organization = group.getOrganization().getName();
		}
	}

	/**
	 * 方法描述 : 增加
	 * @param service
	 * @param organizationService
	 */
	public void add(IGroupService service, IOrganizationService organizationService) {
		IGroup group = service.make(name);
		group.setOrganization(organizationService.get(orgId));
		service.add(group);
		this.id = group.getId();
	}

	/**
	 * 
	 * 方法描述 : 更新
	 * 
	 * @param service
	 */

	public void update(IGroupService service) {
		IGroup group = service.get(id);
		group.setName(name);
		service.update(group);
	}

	/**
	 * 
	 * 方法描述 : 转换
	 * 
	 * @param resources
	 * @return List
	 */

	public static List<GroupForm> convert(List<IGroup> resources) {
		List<GroupForm> result = new ArrayList<GroupForm>();
		for (IGroup resource : resources) {
			result.add(new GroupForm(resource));
		}
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
