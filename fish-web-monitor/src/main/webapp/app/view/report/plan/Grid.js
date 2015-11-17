
Ext.define('Eway.view.report.plan.Grid', {
	alias: 'widget.plan_grid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	
	border : false,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.report.plan.Plan');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: ['->',{
				text: EwayLocale.button.apply,
				iconCls :'connectBtn',
				action: 'link'
			}, {
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
			}, {
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add'
			}, {
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action: 'update'
			}, {
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove'
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : EwayLocale.report.plan.name,
				width : 180,
				dataIndex : 'name'
			},{
				header : EwayLocale.report.plan.startDate,
				width : 180,
				dataIndex : 'startDate'
			},{
				header : EwayLocale.report.plan.endDate,
				width : 180,
				dataIndex : 'endDate'
			},{
				header : EwayLocale.commen.remark,
				dataIndex : 'note',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	}
});