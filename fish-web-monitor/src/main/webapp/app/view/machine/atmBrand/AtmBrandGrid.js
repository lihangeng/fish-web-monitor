
Ext.define('Eway.view.machine.atmBrand.AtmBrandGrid', {
	alias: 'widget.atmBrand_AtmBrandGrid',
	extend: 'Eway.view.base.Grid',


//	store: 'machine.atmBrand.AtmBrand',
	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.machine.atmBrand.AtmBrand');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:Eway.locale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: Eway.locale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'atmBrandAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: Eway.locale.button.update,
				glyph : 0xf040,
				action:'update',
				code : 'atmBrandUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: Eway.locale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'atmBrandDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : Eway.locale.machine.atmBrand.name,
				dataIndex : 'name',
				flex : 1
			},{
				header : Eway.locale.machine.atmBrand.country,
				dataIndex : 'country',
				flex : 2
			},{
				header : Eway.locale.machine.atmBrand.hotline1,
				dataIndex : 'hotline1',
				flex : 1
			},{
				header : Eway.locale.machine.atmBrand.hotline2,
				dataIndex : 'hotline2',
				flex : 1
			},{
				header : Eway.locale.machine.atmBrand.address,
				dataIndex : 'address',
				flex : 3
			},{
				header : Eway.locale.machine.atmBrand.status,
				dataIndex : 'status',
				sortable : true,
				renderer: function(value){
	                   if(value==1){
	                	   return "设备供应";
	                   }else if(value==3){
	                	   return "设备服役";
	                   }
					},
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