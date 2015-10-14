
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
				text: Eway.locale.button.apply,
				iconCls :'connectBtn',
				action: 'link'
			}, {
				text: Eway.locale.button.search,
				glyph : 0xf002,
				action: 'query'
			}, {
				text: Eway.locale.button.add,
				glyph : 0xf067,
				action: 'add'
			}, {
				text: Eway.locale.button.update,
				glyph : 0xf040,
				action: 'update'
			}, {
				text: Eway.locale.button.remove,
				glyph : 0xf014,
				action: 'remove'
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : Eway.locale.report.plan.name,
				width : 180,
				dataIndex : 'name'
			},{
				header : Eway.locale.report.plan.startDate,
				width : 180,
				dataIndex : 'startDate'
			},{
				header : Eway.locale.report.plan.endDate,
				width : 180,
				dataIndex : 'endDate'
			},{
				header : Eway.locale.commen.remark,
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