
Ext.define('Eway.view.person.bankPer.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.bank_person_view',
	
	requires: ['Eway.view.person.bankPer.Grid',
	           'Eway.view.person.bankPer.FilterForm'],
	
	title: EwayLocale.person.bankPer.title,
	layout: 'border',
	
	initComponent: function() {
		var store = Ext.create('Eway.store.person.organization.BankOrganizationTree');     
		Ext.apply(this, {
			items: [{
//				region: 'east',
//				title : EwayLocale.person.bankPer.orgNavi,
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
					xtype: 'bank_person_filterform'
				}, {
					region: 'center',
					xtype: 'bank_person_grid'
				}]
			}],
			listeners : {
				activate : function(panel) {
					if (!panel.isLoad) {
						// 第一次进来主动加载信息
						panel.isLoad = true;
						var store = panel.down('bank_person_grid').getStore();
						store.setUrlParamsByObject({
							type : '0'
						});
						store.load();
						return;
					}
				}
			}
		});
		
		this.callParent(arguments);
	}
});