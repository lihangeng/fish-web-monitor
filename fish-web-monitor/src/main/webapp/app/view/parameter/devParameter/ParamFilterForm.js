Ext.define('Eway.view.parameter.devParameter.ParamFilterForm',{
	extend :'Eway.view.base.FilterForm',
	alias : 'widget.parameter_devParameter_paramFilterForm',
	
	requires:['Eway.view.parameter.devParameter.field.ParamClassify'],
	
	height :57,
	
	initComponent : function(){
		var store=Ext.create('Eway.store.parameter.devParameter.ParamClassify');
		Ext.apply(this,{
			layout :'column',
			msgTarget : 'side',
			style :'padding-top:10px',
			items :[{
				items:[{
					xtype:'devParameter_field_ParamClassify',
					name:'ClassifyId',
					fieldLabel:EwayLocale.param.classify.name,
					store:store
				}]
			},{
				items:[{
					xtype : 'textfield',
					name : 'paramName',
					fieldLabel : EwayLocale.param.element.paramName
				}]
			}]
		});
		this.callParent(arguments);
	}
});