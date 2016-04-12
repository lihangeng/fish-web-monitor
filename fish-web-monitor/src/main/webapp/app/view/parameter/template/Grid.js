
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
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: '新增模板',
				glyph : 0xf067,
				action: 'add',
				code : 'templateAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: '更改模板',
				glyph : 0xf040,
				action:'updateValue',
				code : 'templateUpdateValue',
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
			},{
				text: '关联设备',
				glyph : 0xf0c1,
				action: 'link',
				code : 'templateDevRelation',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: '应用',
				glyph : 0xf040,
				action:'apply',
				code : 'templateApply',
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
				header : '状态',
				dataIndex : 'applyFlag',
				flex : 1,
				renderer: function(value,metadata,record){
					if(value == 0){
		                	 return '未下发';
		             }else if(value == 1){
		                	   return '已经下发';
		             }
				}
			},{
				header : '描述',
				dataIndex : 'remark',
				flex : 2
			}
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