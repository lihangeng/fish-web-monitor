Ext.define('Eway.store.version.JobType', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'MANUAL', display:Eway.locale.version.jobType.float},//'手工作业(立即下发)'},
	       {value:'SCHEDULER', display:Eway.locale.version.jobType.fix}//'计划作业(定时下发)'}
//	       ,{value:'AUTO_UPDATE', display:'自动更新作业'}
	      ]
});
