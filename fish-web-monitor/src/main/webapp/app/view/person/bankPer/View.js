
Ext.define('Eway.view.person.bankPer.View', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.bank_person_view',
	
	requires: ['Eway.view.person.bankPer.Grid',
	           'Eway.view.person.bankPer.FilterForm'],
	
	title: '银行人员管理',
	layout: 'border',
	
	initComponent: function() {
		var store = Ext.create('Eway.store.person.organization.BankOrganizationTree');     
		Ext.apply(this, {
			items: [{
				region: 'east',
				title : '机构导航',
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
					xtype: 'bank_person_filterform'
				}, {
					region: 'center',
					xtype: 'bank_person_grid'
				}]
			}],
			listeners : {
				activate : function(panel) {
					if (!panel.isLoad) {
						
						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					// 刷新所属机构信息
//					if(ewayUser.getOrgType()=='0'){
//						panel.down('treepanel').setRootNode({
//							id: ewayUser.getOrgId(),
//							text: ewayUser.getOrgName(),
//							expanded: true
//						});
//					}
					panel.down('treepanel').getStore().load();
				}
			}
		});
		
		this.callParent(arguments);
	}
});