
Ext.define('Eway.view.machine.atmBrand.AtmBrandGrid', {
	alias: 'widget.atmBrand_AtmBrandGrid',
	extend: 'Eway.view.base.Grid',


//	store: 'machine.atmBrand.AtmBrand',
	border : true,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.machine.atmBrand.AtmBrand');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:EwayLocale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'atmBrandAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action:'update',
				code : 'atmBrandUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.remove,
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
				header : EwayLocale.machine.atmBrand.name,
				dataIndex : 'name',
				flex : 1
			},{
				header : EwayLocale.machine.atmBrand.country,
				dataIndex : 'country',
				flex : 2
			},{
				header : EwayLocale.machine.atmBrand.hotline1,
				dataIndex : 'hotline1',
				flex : 1
			},{
				header : EwayLocale.machine.atmBrand.hotline2,
				dataIndex : 'hotline2',
				flex : 1
			},{
				header : EwayLocale.machine.atmBrand.address,
				dataIndex : 'address',
				flex : 3
			}/*,{
				header : EwayLocale.machine.atmBrand.status,
				dataIndex : 'status',
				sortable : true,
				renderer: function(value){
	                   if(value==1){
	                	   return EwayLocale.machine.atmBrand.comboxStatus.provider;
	                   }else if(value==3){
	                	   return EwayLocale.machine.atmBrand.comboxStatus.maintance;
	                   }
					},
				flex : 1
			}*/],
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