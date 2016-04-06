Ext.define('Eway.view.parameter.template.ParamValueGrid', {
	alias : 'widget.param_paramValueGrid',
	extend : 'Eway.view.base.Grid',

	border : false,
	autoFit : true,
	plugins : {
			ptype : 'cellediting',
			clicksToEdit: 1
	},
	
	initComponent : function() {

		var store = Ext.create('Eway.store.parameter.template.TemplateDetail');
		store.setUrlParamsByObject({'id':this.templateId});
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			frame: true,  
			store : store,
			columns : [
			    {
				header : '模板名称',
				dataIndex : 'templateName',
				flex : 1,
				storable : true
			},  {
				header : '参数名称',
				dataIndex : 'paramName',
				flex : 1,
				storable : true
			},{
				header : '参数值',
				dataIndex : 'paramValue',
				flex : 1,
				editor:new Ext.form.TextField({  
	                allowBlank:false  
	            })
			}, {
				header : '所属系统',
				dataIndex : 'paramBelongs',
				flex : 1.5,
				storable : true
			} ]
		});
		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});