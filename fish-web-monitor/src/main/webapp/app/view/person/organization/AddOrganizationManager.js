/**
 * 机构管理员设置窗口：
 */
Ext.define('Eway.view.person.organization.AddOrganizationManager', {
	alias: 'widget.person_organization_addOrganizationManager',
	extend: 'Ext.window.Window',
	
	title: '该机构人员信息',
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	
	requires: ['Eway.view.person.organization.OrganizationManagerGrid'],
	
	width: 750,
	height: 350,
	layout : 'fit',
	initComponent: function() {
		Ext.apply(this, {
			title : '人员列表',
			items : [{
				xtype : 'Organization_organizationManagerGrid',
				autoLoadStore : true
			}]
		});
		this.callParent(arguments);
	}
	
});