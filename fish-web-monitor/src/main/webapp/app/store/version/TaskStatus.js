Ext.define('Eway.store.version.TaskStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'NEW', display:'新建'}, 
	       {value:'RUN', display:'运行中'}, 
	       {value:'NOTICED_SUCCESS', display:'通知成功'},
	       {value:'NOTICED_FAIL', display:'通知失败'},
	       {value:'DOWNLOADED', display:'已下发'}]
});
