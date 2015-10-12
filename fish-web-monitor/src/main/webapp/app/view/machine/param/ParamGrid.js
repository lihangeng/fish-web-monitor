
Ext.define('Eway.view.machine.param.ParamGrid', {
	alias: 'widget.param_ParamGrid',
	extend: 'Eway.view.base.Grid',
	
	
	store: 'machine.param.Param',
	border : false,
	autoFit:true,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.machine.param.Param');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:Eway.locale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: Eway.locale.button.update,
				glyph : 0xf040,
				action:'update',
				code : 'configurationUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : Eway.locale.machine.param.paramKey,
				dataIndex : 'paramKey',
				width : 150
			}, {
				header : Eway.locale.machine.param.paramValue,
				dataIndex : 'paramValue',
				width : 250
			},{
				header: Eway.locale.machine.param.classify,
				dataIndex: 'classify',
				width : 150,
				renderer: function(value){
					if(value == 0){
						return Eway.locale.machine.param.comboxClassify.unableUpdate;
					}else if(value == 1){
						return Eway.locale.machine.param.comboxClassify.ableUpdate;
					}
				}
			},{
				header : Eway.locale.machine.param.description,
				dataIndex : 'description',
				flex :1
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