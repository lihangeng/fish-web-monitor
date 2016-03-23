
Ext.define('Eway.view.parameter.template.Grid', {
	alias: 'widget.template_Grid',
	extend: 'Eway.view.base.Grid',

	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.parameter.template.Template');
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
				code : 'templateAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action:'update',
				code : 'templateUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'templateDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : '参数模板名称',
				dataIndex : 'name',
				flex : 1
			},{
				header : '参数值',
				dataIndex : 'remark',
				flex : 1
			}
//			,{
//				header : EwayLocale.machine.atmBrand.hotline1,
//				dataIndex : 'hotline1',
//				flex : 1
//			},{
//				header : EwayLocale.machine.atmBrand.hotline2,
//				dataIndex : 'hotline2',
//				flex : 1
//			},{
//				header : EwayLocale.machine.atmBrand.address,
//				dataIndex : 'address',
//				flex : 3
//			}
			],
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