
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
				text: '应用',
				iconCls :'connectBtn',
				action: 'link'
			}, {
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			}, {
				text: '增加',
				glyph : 0xf067,
				action: 'add'
			}, {
				text: '更改',
				glyph : 0xf040,
				action: 'update'
			}, {
				text: '删除',
				glyph : 0xf014,
				action: 'remove'
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : '名称',
				width : 180,
				dataIndex : 'name'
			},{
				header : '有效开始时间',
				width : 180,
				dataIndex : 'startDate'
			},{
				header : '有效结束时间',
				width : 180,
				dataIndex : 'endDate'
			},{
				header : '备注',
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