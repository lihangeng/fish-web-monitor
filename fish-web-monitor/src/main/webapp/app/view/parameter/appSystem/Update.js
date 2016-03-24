Ext.define('Eway.view.parameter.appSystem.Update',{
	
	extend : 'Eway.view.base.Form',
	
	alias : 'widget.parameter_appSystem_update',
	requires:['Eway.view.parameter.appSystem.field.FileFormCombox'],
	          
	initComponent:function(){
		var format=Ext.create('Eway.store.parameter.appSystem.FileForm');
		Ext.apply(this,{
				defaults: {
					width: 400,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items:[{
					fieldLabel:'配置文件名称',
					xtype:'textfield',
					name:'configName',
					allowBlank:false,
					maxLength : 80
				},{
					fieldLabel:'配置文件格式',
					xtype:'field_appSystem_fileForm',
					allowBlank:false,
					store:format
				},{
					fieldLabel:'配置文件路径',
					xtype:'textfield',
					allowBlank:false,
					name:'configPath',
					regex:/^[a-zA-z]{1}:([/][\w-]+)*$/i
				},{
					fieldLabel:'备注',
					xtype:'textfield',
					allowBlank:false,
					name:'remark',
					maxLength : 40
				}]
		});
		this.callParent(arguments);
	}
});
