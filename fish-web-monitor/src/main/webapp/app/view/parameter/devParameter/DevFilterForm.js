Ext.define('Eway.view.parameter.devParameter.DevFilterForm',{
	extend :'Eway.view.base.FilterForm',
	alias : 'widget.parameter_devParameter_devFilterForm',
	
	height :50,
	
	initComponent : function(){
		Ext.apply(this,{
			layout :'column',
			msgTarget : 'side',
//			style :'padding-top:10px',
			items :[{
				xtype : 'textfield',
				name : 'terminalId',
				fieldLabel : '设备号'
			}/*,{
				xtype : 'textfield',
				name : 'ip',
				fieldLabel : 'IP地址',
				labelWidth:80
				
			}*//*,{
				xtype : 'textfield',
				name : 'orgName',
				fieldLabel : '机构名'
				
			}*/]
		});
		this.callParent(arguments);
	}
});