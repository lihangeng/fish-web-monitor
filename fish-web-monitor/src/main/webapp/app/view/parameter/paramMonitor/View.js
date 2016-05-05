Ext.define('Eway.view.parameter.paramMonitor.View',{
	extend:'Eway.view.base.Panel',
	alias: 'widget.parameter_paramMonitor_view',
	
	requires : [ 'Eway.view.parameter.paramMonitor.JobView','Eway.view.parameter.paramMonitor.TaskView'],
	
	title:EwayLocale.param.paramDownloadMonitor.title,
	layout:'border',
	initComponent:function(){
		
		Ext.apply(this,{
			items:[{
				region:'center',
				xtype:'tabpanel',
				tabPosition : 'bottom',
				headerPosition: 'bottom',
				items:[{
					name:'taskPanel',
					title:EwayLocale.param.paramDownloadMonitor.jobName,
					closable:false,
					region:'center',
					xtype:'parameter_paramMonitor_JobView'
				}]
			}]
			
			 
		 });
		this.callParent(arguments);
	}
	
});

