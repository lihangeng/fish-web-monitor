Ext.define('Eway.view.parameter.appSystem.FilterForm',{
	extend :'Eway.view.base.FilterForm',
	alias : 'widget.parameter_appSystem_filterForm',
	
	store:['Eway.store.parameter.appSystem.FileFormat'],
	requires:['Eway.view.parameter.appSystem.field.FileFormatCombox'],
	
	height :30,
	
	initComponent : function(){
		var format=Ext.create('Eway.store.parameter.appSystem.FileFormat');
		Ext.apply(this,{
			layout :'column',
			items :[{
				columnWidth :0.3,
				items:[{
					xtype:'textfield',
					fieldLabel:'名称',
					name:'name',
					nameLenght:20
				}],
			},{
				columnWidth :.3,
				items:[{
					xtype :'textfield',
					fieldLabel:'配置文件名称',
					name:'configName',
					nameLength:20
				}]
				
			},{
				columnWidth :.3,
				items:[{
					xtype:'field_appSystem_fileFormat',
					fieldLabel:'配置文件格式',
					name:'configForm',
					nameLenght:20,
					store:format
				}]
				
			}
					
		]
		});
		this.callParent(arguments);
	}
});