Ext.define('Eway.view.parameter.appSystem.Update',{

	extend : 'Eway.view.base.Form',

	alias : 'widget.parameter_appSystem_update',
	requires:['Eway.view.parameter.appSystem.field.FileFormatCombox'],

	initComponent:function(){
		var format=Ext.create('Eway.store.parameter.appSystem.FileFormat');
		Ext.apply(this,{
				defaults: {
					width: 400,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items:[{
					fieldLabel:EwayLocale.param.application.configureName,
					xtype:'textfield',
					name:'configName',
					allowBlank:false,
					maxLength : 64
				}/*,{
					fieldLabel:EwayLocale.param.application.configureForm,
					xtype:'field_appSystem_fileFormat',
					allowBlank:false,
					store:format
				}*/,{
					fieldLabel:EwayLocale.param.application.configurePath,
					xtype:'textfield',
					allowBlank:false,
					name:'configPath',
					maxLength : 128,
					regex:/^[a-zA-z]{1}:([/][\w-]+)*$/i
				},{
					fieldLabel:EwayLocale.param.application.remark,
					xtype:'textarea',
					allowBlank:false,
					name:'remark',
					maxLength : 128
				}]
		});
		this.callParent(arguments);
	}
});
