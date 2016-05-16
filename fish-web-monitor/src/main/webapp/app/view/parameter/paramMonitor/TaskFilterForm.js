Ext.define('Eway.view.parameter.paramMonitor.TaskFilterForm',{
	extend :'Eway.view.base.FilterForm',
	alias : 'widget.parameter_paramMonitor_TaskFilterForm',
	height :50,
	
	initComponent : function(){
		Ext.apply(this,{
			layout :'column',
			msgTarget : 'side',
			items:[{
				columnWidth : .5,
				items:[{
					xtype : 'textfield',
					name : 'id',
					fieldLabel : EwayLocale.param.paramDownloadMonitor.taskId,
					regex : /(^([0-9]\d{0,17})$)/
				}]
			},{
				columnWidth : .5,
				items:[{
					xtype : 'textfield',
					name : 'terminalId',
					fieldLabel :'设备终端号',
				}]
			}]	
		});
		this.callParent(arguments);
	}
});