
Ext.define('Eway.view.person.servicePer.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.ser_person_view',

	requires: ['Eway.view.person.servicePer.Grid',
	           'Eway.view.person.servicePer.FilterForm'],

	title: EwayLocale.person.servicePer.title,
	layout: 'border',

	initComponent: function() {
		var store = Ext.create('Eway.store.person.organization.SerOrganizationTree');
		Ext.apply(this, {
			items: [{
//				region: 'east',
//				title : EwayLocale.person.serviceOrg.serviceNavi,
//				header: false,
//				xtype : 'treepanel',
//				width : 200,
//				border : false,
//				padding : '0 2 0 0',
//				lines : true,
//				store : store
//			}, {
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
						// 第一次进来主动加载信息
						panel.isLoad = true;
						var store = panel.down('ser_person_grid').getStore();
						store.setUrlParamsByObject({
							type : '1'
						});
						store.load();
//						panel.down('ser_person_grid').getStore().load();
						return;
					}
				}
			}
		});

		this.callParent(arguments);
	}
});