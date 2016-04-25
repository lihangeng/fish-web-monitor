
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
				text: EwayLocale.button.addParamTemplate,
				glyph : 0xf067,
				action: 'add',
				code : 'templateAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.updateParamTemplate,
				glyph : 0xf040,
				action:'updateValue',
				code : 'templateUpdateValue',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.removeParamTemplate,
				glyph : 0xf014,
				action: 'remove',
				code : 'templateDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.bankPerlink,
				glyph : 0xf0c1,
				action: 'link',
				code : 'templateDevRelation',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.applyParamTemplate,
				glyph : 0xf0ed,
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
				header : EwayLocale.param.template.paramTemplateName,
				dataIndex : 'name',
				flex : 1
			},{
				header : EwayLocale.param.template.applyFlag,
				dataIndex : 'applyFlag',
				flex : 1,
				renderer: function(value,metadata,record){
					if(value == 0){
		                	 return EwayLocale.param.template.unpublished;
		             }else if(value == 1){
		                	   return EwayLocale.param.template.published;
		             }
				}
			},{
				header : EwayLocale.param.template.templateRemark,
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