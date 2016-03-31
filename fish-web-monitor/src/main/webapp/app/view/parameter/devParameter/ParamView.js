Ext.define('Eway.view.parameter.devParameter.ParamView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.parameter_devParameter_ParamView',

	requires : [ 'Eway.view.parameter.devParameter.ParamFilterForm',
			     'Eway.view.parameter.devParameter.ParamGrid' ],
	config:{
		appType:''
	},
	layout : 'border',
	name:'devParamDetails',
	margin:'-9 0 0 0',
	initComponent : function() {
		Ext.apply(this, {
			items : [{
				region:'north',
				xtype:'parameter_devParameter_paramFilterForm'
			},{
				region:'center',
				xtype:'parameter_devParameter_paramGrid'
			}],
			listeners:{
				render:function(_this,a){
					var ok = _this.getConfig().appType;
					//alert(ok);
				}
			}
		});

		this.callParent(arguments);
	}
});