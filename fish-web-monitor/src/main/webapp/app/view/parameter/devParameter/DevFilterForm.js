Ext.define('Eway.view.parameter.devParameter.DevFilterForm',{
	extend :'Eway.view.base.FilterForm',
	alias : 'widget.parameter_devParameter_devFilterForm',
	requires:['Eway.view.field.device.DeviceAtmType'],
	height :50,
	
	initComponent : function(){
		var typeStore=Ext.create('Eway.store.machine.DeviceAtmType');
		Ext.apply(this,{
			layout :'column',
			msgTarget : 'side',
			items :[{
				columnWidth : .3,
				items:[{
					xtype : 'textfield',
					name : 'terminalId',
					fieldLabel : EwayLocale.commen.terminalId
				}]	
			},{
				columnWidth:.3,
				items:[{
					xtype : 'textfield',
					name : 'ip',
					fieldLabel : EwayLocale.commen.ip,
					regex : /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,
					regexText : EwayLocale.vtype.ip,
					msgTarget : 'side'
				}]
			},{
				columnWidth: .3,
				items:[{
					xtype : 'field_device_deviceatmtype',
					name : 'devTypeId',
					fieldLabel : EwayLocale.commen.devTypeName,
					store:typeStore
				}]
				
			}]
		});
		this.callParent(arguments);
	}
});