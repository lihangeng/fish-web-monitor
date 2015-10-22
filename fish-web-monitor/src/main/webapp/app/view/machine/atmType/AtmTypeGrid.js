
Ext.define('Eway.view.machine.atmType.AtmTypeGrid', {
	alias: 'widget.atmType_AtmTypeGrid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],

	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.machine.atmType.AtmType');
		store.loadPage(1);
		Ext.apply(this,{
			store : store,
			initRegion : true,
			tbar: ['->',{
					text: Eway.locale.button.search,
					glyph : 0xf002,
					action:'query'
				},{
					text: Eway.locale.button.add,
					glyph : 0xf067,
					action: 'add',
					code : 'atmTypeAdd',
					listeners:{
						'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
					}
				},{
					text: Eway.locale.button.update,
					glyph : 0xf040,
					action:'update',
					code : 'atmTypeUpdate',
					listeners:{
						'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
					}
				},{
					text: Eway.locale.button.remove,
					glyph : 0xf014,
					action: 'remove',
					code : 'atmTypeDel',
					listeners:{
						'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
					}
				}],
			columns : [/*{
				header : '编号',
				dataIndex : 'no',
				flex : 1
			}, */{
				header : Eway.locale.machine.atmType.name,
				dataIndex : 'name',
				flex : 1
			}, {
				header : Eway.locale.machine.atmType.devVendorName,
				dataIndex : 'devVendorName',
				flex : 1
			},{
				header : Eway.locale.machine.atmType.devCatalogName,
				dataIndex : 'devCatalogName',
				flex : 1
			},{
				header : Eway.locale.machine.atmType.spec,
				dataIndex : 'spec',
				flex : 1
			},{
				header : Eway.locale.machine.atmType.weight,
				dataIndex : 'weight',
				flex : 1
			},{
				header : Eway.locale.machine.atmType.watt,
				dataIndex : 'watt',
				flex : 1
			},{
				header : Eway.locale.machine.atmType.cashtype,
				dataIndex : 'cashtype',
				flex : 1,
				renderer: function(value,metadata,record){
	                   if(value==1){
	                	   return Eway.locale.machine.atmType.iscash;
	                   }else if(value==2){
	                	   return Eway.locale.machine.atmType.nocash;
	                   }
					}
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