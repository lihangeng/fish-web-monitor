Ext.define('Eway.store.version.TaskStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'NEW', display:Eway.locale.commen.stateDict.newCreate}, 
	       {value:'RUN', display:'运行中'}, 
	       {value:'NOTICED', display:'通知成功'},
	       {value:'NOTICED_FAIL', display:'通知失败'},
	       {value:'DOWNLOADED', display:'已下发'},
	       {value:'DOWNLOADED_FAIL', display:'下发失败'},
	       {value:'DEPLOYED', display:'正在部署'},
	       {value:'DEPLOYED_WAIT', display:'正在部署'},
	       {value:'DEPLOYED_FAIL', display:'部署失败'},
	       {value:'CHECKED', display:'部署已确认'},
	       {value:'NOTICE_APP_OK', display:'已通知应用'},
	       {value:'NOTICE_APP_FAIL', display:'通知应用失败'}]

});
