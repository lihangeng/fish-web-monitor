Ext.define('Eway.view.parameter.devParameter.ParamFilterForm',{
	extend :'Eway.view.base.FilterForm',
	alias : 'widget.parameter_devParameter_paramFilterForm',
	
	requires:['Eway.view.parameter.devParameter.field.ParamBelongs'],
	
	height :79,
	
	initComponent : function(){
		var store=Ext.create('Eway.store.parameter.devParameter.ParamBelongs');
		Ext.apply(this,{
			layout :'column',
			msgTarget : 'side',
			style :'padding-top:10px',
			items :[{
				items:[{
					xtype:'devParameter_field_ParamBelongs',
					name:'paramBelongs',
					fieldLabel:'参数所属系统',
					store:store
				}]
			},{
				items:[{
					xtype : 'textfield',
					name : 'paramName',
					fieldLabel : '参数名称'
				}]
			}]
		});
		this.callParent(arguments);
	}
});