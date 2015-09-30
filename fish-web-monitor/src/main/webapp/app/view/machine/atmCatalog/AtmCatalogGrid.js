
Ext.define('Eway.view.machine.atmCatalog.AtmCatalogGrid', {
	alias: 'widget.atmCatalog_AtmCatalogGrid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	
	border : false,
	autoFit:true,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.machine.atmCatalog.AtmCatalog');
		store.loadPage(1);
		Ext.apply(this,{
			store : store,
			initRegion : true,
			tbar: ['->',{
				text:Eway.locale.button.search,
				glyph : 0xf002,
				action:'query'
			}
//			{
//				text: '添加', 
//				glyph : 0xf067,
//				action: 'add'
//			},
//			{
//				text: '删除',
//				glyph : 0xf014,
//				action: 'remove'
//			},
//			{
//				text: '更改',
//				glyph : 0xf040,
//				action:'update'
//			},
			],
			columns : [{
				header : Eway.locale.machine.atmCatalog.name,
				dataIndex : 'name'
			},{
				header : Eway.locale.machine.atmCatalog.note,
				dataIndex : 'note',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	},
	
	onReload: function() {
		this.getStore().load();
	}
});