
Ext.define('Eway.view.person.servicePer.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.ser_person_view',

	requires: ['Eway.view.person.servicePer.Grid',
	           'Eway.view.person.servicePer.FilterForm'],

	title: '维护人员管理',
	layout: 'border',

	initComponent: function() {
		var store = Ext.create('Eway.store.person.organization.SerOrganizationTree');
		Ext.apply(this, {
			items: [{
				region: 'east',
				title : '厂商导航',
				header: false,
				xtype : 'treepanel',
				width : 200,
				border : false,
				padding : '0 2 0 0',
				lines : true,
				store : store
			}, {
				region: 'center',
				xtype: 'panel',
				layout: 'border',
				items: [{
					region: 'north',
					xtype: 'ser_person_filterform'
				}, {
					region: 'center',
					xtype: 'ser_person_grid'
				}]
			}],
			listeners : {
				activate : function(panel) {
					if (!panel.isLoad) {

						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					// 刷新维护商信息
					if(ewayUser.getOrgType()=='1'){
						panel.down('treepanel').setRootNode({
							id: ewayUser.getOrgId(),
							text: ewayUser.getOrgName(),
							expanded: true
						});
					}
					panel.down('treepanel').getStore().load();
				}
			}
		});

		this.callParent(arguments);
	}
});