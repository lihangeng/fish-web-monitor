
Ext.define('Eway.view.cash.initRule.Grid', {
	alias: 'widget.initRule_grid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	initComponent: function() {
		var store = Ext.create('Eway.store.cash.initRule.CashInitRule');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store: store,
			tbar: ['->', {
				text: EwayLocale.button.search,//'查询',
				action: 'query',
				code:'alarmRuleSeach',
				glyph : 0xf002,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.update,//'更改',
				action: 'update',
				code:'alarmRuleUpdate',
				glyph : 0xf040,
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [ {
//				'id','name','ruleDesc','startUsing'
				header :  EwayLocale.initRule.ruleName,
				dataIndex : 'name',
				flex : 1
			}, {
				header :  EwayLocale.initRule.ruleDesc,
				dataIndex : 'ruleDesc',
				flex : 1
			}, {
				header :  EwayLocale.initRule.startUsing,
				dataIndex : 'startUsing',
				renderer : function(value){
					if(value == true){
						return EwayLocale.initRule.status.open;
					}else{
						return EwayLocale.initRule.status.close;
					}
				},
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