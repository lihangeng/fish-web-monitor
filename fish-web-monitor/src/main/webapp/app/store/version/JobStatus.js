Ext.define('Eway.store.version.JobStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'NEW', display:'新建'}, 
	       {value:'RUN', display:'运行中'}, 
	       {value:'SCHEDULER', display:'计划中'},
	       {value:'READY_RUN', display:'准备运行'},
	       {value:'PAUSE', display:'暂停'},
	       {value:'COMPLETE', display:'完成'}]
});
