Ext.define('Eway.view.parameter.paramMonitor.TaskView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.parameter_paramMonitor_TaskView',

	requires : [ 'Eway.view.parameter.paramMonitor.TaskFilterForm',
			     'Eway.view.parameter.paramMonitor.TaskGrid' ],
	
	layout:'border',
	name:'paramMonitorDetails',
	initComponent:function(){
		Ext.apply(this,{
			 items:[{
				 region : 'north',
				 xtype : 'parameter_paramMonitor_TaskFilterForm'
			 },{
				 region : 'center',
				 xtype : 'parameter_paramMonitor_TaskGrid'
			 }]
		 });
		this.callParent(arguments);
	}
});