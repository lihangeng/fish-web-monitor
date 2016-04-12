Ext.define('Eway.view.parameter.appSystem.Grid',{
	extend : 'Eway.view.base.Grid',
	alias : 'widget.parameter_appSystem_grid',

	border : false,

	initComponent : function(){
		var store=Ext.create('Eway.store.parameter.appSystem.AppSystem');
		store.loadPage(1);
		Ext.apply(this,{
			store:store,
			initRegion:true,
			tbar : ['->',{
				text:EwayLocale.button.search,
				action:'query',
				glyph : 0xf002,
				code : 'appSystemQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text:EwayLocale.button.update,
				action :'update',
				glyph : 0xf040,
				code : 'appSystemUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : EwayLocale.param.application.name,
				dataIndex :'name',
				width :80,
				sortable:true
			},{
				header :EwayLocale.param.application.configureName,
				dataIndex :'configName',
				width :190,
				sortable:true
			},{
				header :EwayLocale.param.application.configureForm,
				dataIndex :'configForm',
				width :180,
				sortable:true,
				renderer:function(value,metadata,record){
					if(value==0){
						return "JSON";
					}
					if(value==1){
						return "PROPERTIES";
					}
					if(value==2){
						return "INI";
					}
					if(value==3){
						return "XML";
					}
				}
			},{
				header :EwayLocale.param.application.configurePath,
				dataIndex : 'configPath',
				width :190
			},{
				header :EwayLocale.param.application.remark,
				dataIndex :'remark',
				flex:1
			}],
			bbar :Ext.create('Ext.PagingToolbar',{
				store :store,
				displayInfo:true
			})
		});
		this.callParent(arguments);
	}
});
