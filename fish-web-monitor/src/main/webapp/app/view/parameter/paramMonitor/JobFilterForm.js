Ext.define('Eway.view.parameter.paramMonitor.JobFilterForm',{
	extend :'Eway.view.base.FilterForm',
	alias : 'widget.parameter_paramMonitor_JobFilterForm',
	height :50,
	
	initComponent : function(){
		Ext.apply(this,{
			layout :'column',
			msgTarget : 'side',
//			items :[{
				columnWidth : .3,
				items:[{
					xtype : 'textfield',
					name : 'id',
					fieldLabel : '作业ID'
				}]	
		/*	},{
				columnWidth:.3,
				items:[{
					xtype : 'textfield',
					name : 'ip',
					fieldLabel : EwayLocale.commen.ip,
				}]
			},{
				columnWidth: .3,
				items:[{
					xtype : 'field_device_deviceatmtype',
					name : 'devTypeId',
					fieldLabel : EwayLocale.commen.devTypeName,
					store:typeStore
				}]
				
			}]*/
		});
		this.callParent(arguments);
	}
});