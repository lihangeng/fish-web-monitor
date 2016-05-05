Ext.define('Eway.view.parameter.paramMonitor.TaskFilterForm',{
	extend :'Eway.view.base.FilterForm',
	alias : 'widget.parameter_paramMonitor_TaskFilterForm',
	height :50,
	
	initComponent : function(){
		Ext.apply(this,{
			layout :'column',
			msgTarget : 'side',
				columnWidth : .3,
				items:[{
					xtype : 'textfield',
					name : 'id',
					fieldLabel :EwayLocale.param.paramDownloadMonitor.taskId,
					regex:/(^([0-9]\d{0,17})$)/
				}]	
		});
		this.callParent(arguments);
	}
});