
Ext.define('Eway.view.machine.atmModule.AtmModuleGrid', {
	alias: 'widget.atmModule_AtmModuleGrid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	
	store: 'machine.atmModule.AtmModule',
//	id : 'atmModuleGridId',
	border : false,
	autoFit:true,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.machine.atmModule.AtmModule');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:'查询',
				glyph : 0xf002,
				action:'query'
			}],
			columns : [/*{
				header : '模块编号',
				dataIndex : 'no'
			}, */{
				header : '模块名称',
				dataIndex : 'name'
			}, {
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
	},
	
	onReload: function() {
		this.getStore().load();
	}
});