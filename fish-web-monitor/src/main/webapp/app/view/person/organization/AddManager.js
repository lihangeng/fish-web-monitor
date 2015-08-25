/**
 * 机构管理员设置窗口：
 */
Ext.define('Eway.view.person.organization.AddManager', {
	alias: 'widget.person_organization_addManager',
	extend: 'Ext.window.Window',
	
	title: '该机构人员信息',
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	
	requires: ['Eway.view.person.organization.OrganizationPersonGrid'],
	
	width: 750,
	height: 350,
	layout : 'fit',
	initComponent: function() {
		Ext.apply(this, {
			title : '人员列表',
			items : [{
				xtype : 'Organization_organizationPersonGrid',
				autoLoadStore : true
			}]
		});
		this.callParent(arguments);
	}
	
});