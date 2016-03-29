Ext.define('Eway.view.parameter.template.ParamValueGrid', {
	alias : 'widget.param_paramValueGrid',
	extend : 'Eway.view.base.Grid',

	store : 'parameter.template.AddingParam',
	border : false,
	autoFit : true,

	plugins : {
			ptype : 'cellediting',
			clicksToEdit: 1
	},
	
	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			frame: true,  
			columns : [
			    {
				header : '参数名称',
				dataIndex : 'paramName',
				flex : 1,
				storable : true
			}, {
				header : '参数值',
				dataIndex : 'paramValue',
				flex : 1
			}, {
				header : 'ID',
				dataIndex : 'id',
				hidden : true,
				storable : true
			} ]

		});
		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});