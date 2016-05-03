Ext.define('Eway.view.parameter.paramMonitor.JobView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.parameter_paramMonitor_JobView',

	requires : [ 'Eway.view.parameter.paramMonitor.JobFilterForm',
			     'Eway.view.parameter.paramMonitor.JobGrid' ],
	
	layout:'border',
	initComponent:function(){
		
		Ext.apply(this,{
			 items:[{
				 region : 'north',
				 xtype : 'parameter_paramMonitor_JobFilterForm'
			 },{
				 region : 'center',
				 xtype : 'parameter_paramMonitor_JobGrid'
			 }]
		 });
		this.callParent(arguments);
	}
});