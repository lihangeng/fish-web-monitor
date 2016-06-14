Ext.define('Eway.view.parameter.paramMonitor.View',{
	extend:'Eway.view.base.Panel',
	alias: 'widget.parameter_paramMonitor_view',
	
	requires : [ 'Eway.view.parameter.paramMonitor.JobView','Eway.view.parameter.paramMonitor.TaskView'],
	
	title:EwayLocale.param.paramDownloadMonitor.title,
	layout:'card',
	initComponent:function(){
		
		Ext.apply(this,{
			items:[{
				xtype:'parameter_paramMonitor_JobView'
			},{
				xtype:'parameter_paramMonitor_TaskView'
			}]
			
			 
		 });
		this.callParent(arguments);
	}
	
});

