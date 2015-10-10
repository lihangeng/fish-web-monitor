
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
				text:'查询',
				glyph : 0xf002,
				action:'query'
			},{
				text: '更改',
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
				header : '参数',
				dataIndex : 'paramKey',
				width : 150
			}, {
				header : '参数值',
				dataIndex : 'paramValue',
				width : 250
			},{
				header: Eway.locale.commen.type,
				dataIndex: 'classify',
				width : 150,
				renderer: function(value){
					if(value == 0){
						return '不可修改';
					}else if(value == 1){
						return '可以修改';
					}
				}
			},{
				header : '参数信息描述',
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