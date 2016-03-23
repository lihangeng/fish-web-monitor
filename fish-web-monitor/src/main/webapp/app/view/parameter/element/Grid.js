
Ext.define('Eway.view.parameter.element.Grid', {
	alias: 'widget.element_Grid',
	extend: 'Eway.view.base.Grid',

	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.parameter.element.Element');
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
				header : '参数名',
				dataIndex : 'paramName',
				flex : 1
			},{
				header : '参数值',
				dataIndex : 'paramValue',
				flex : 2
			},{
				header : '分类',
				dataIndex : 'paramValue',
				flex : 2
			},{
				header : '版本号',
				dataIndex : 'paramValue',
				flex : 2
			},{
				header : '参数权限',
				dataIndex : 'paramValue',
				flex : 2
			},{
				header : '备注',
				dataIndex : 'paramValue',
				flex : 2
			},{
				header : '创建时间',
				dataIndex : 'paramValue',
				flex : 2
			},{
				header : '最后修改时间',
				dataIndex : 'paramValue',
				flex : 2
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