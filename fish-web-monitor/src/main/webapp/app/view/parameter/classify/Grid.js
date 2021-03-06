Ext.define('Eway.view.parameter.classify.Grid', {
	extend: 'Eway.view.base.Grid',
	alias: 'widget.classify_Grid',

	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.parameter.classify.Classify');
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
				code : 'paramClassifyAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action:'update',
				code : 'paramClassifyUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'paramClassifyDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : EwayLocale.param.classify.name,
				dataIndex : 'name',
				flex : 1
			},{
				header : EwayLocale.param.classify.remark,
				dataIndex : 'remark',
				flex : 2
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