package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.person.IOrganization;

/**
 * @author YiHua
 *
 */
public class MasterManagedOrgForm {

		private String id;
		private String text;
		private boolean leaf = true;
		private boolean checked;
		
		public MasterManagedOrgForm() {
			
		}
		
		/**
		 * @param organization
		 * @param checked
		 */
		public MasterManagedOrgForm(IOrganization organization,boolean checked) {
			id = organization.getCode();
			this.checked = checked;
			text = organization.getName();
			leaf = !(organization.listChildren().iterator().hasNext());
		}
		
		/**
		 * @param data
		 * @return
		 */
		public static List<OrganizationTreeForm> convert(Iterable<IOrganization> data) {
			List<OrganizationTreeForm> result = new ArrayList<OrganizationTreeForm>();
			for(IOrganization item : data) {
				result.add(new OrganizationTreeForm(item));
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

}
