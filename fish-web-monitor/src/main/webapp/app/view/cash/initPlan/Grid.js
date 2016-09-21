
Ext.define('Eway.view.cash.initPlan.Grid', {
	alias: 'widget.initPlan_grid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	initComponent: function() {
		var store = Ext.create('Eway.store.cash.initPlan.CashInitPlan');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store: store,
			tbar: ['->', {
				text: EwayLocale.button.search,//'查询',
				action: 'query',
				code:'cashInitPlanSeach',
				glyph : 0xf002,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.info,//'详细信息',
				action: 'update',
				code:'cashInitPlanDetail',
				glyph : 0xf040,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [ {
				header :  EwayLocale.initPlan.initDate,
				dataIndex : 'date',
				flex : 1
			}, {
				header : EwayLocale.initPlan.cashInitCode,
				dataIndex : 'cashInitCode',
				flex : 1
			}, {
				header :  EwayLocale.initPlan.orgName,
				dataIndex : 'orgName',
				flex : 1
			}, {
				header :  EwayLocale.initPlan.actualAmtAll,
				dataIndex : 'amt',
				flex : 1
			}
			],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	}
});