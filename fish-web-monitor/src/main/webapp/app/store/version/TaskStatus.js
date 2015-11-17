Ext.define('Eway.store.version.TaskStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'NEW', display:EwayLocale.version.taskStatus.news}, 
	       {value:'RUN', display:EwayLocale.version.taskStatus.running}, //'运行中'}, 
	       {value:'NOTICED', display:EwayLocale.version.taskStatus.noticed}, //'通知成功'},
	       {value:'NOTICED_FAIL', display:EwayLocale.version.taskStatus.noticedFail}, //'通知失败'},
	       {value:'DOWNLOADED', display:EwayLocale.version.taskStatus.downloaded}, //'已下发'},
	       {value:'DOWNLOADED_FAIL', display:EwayLocale.version.taskStatus.downloadedFail}, //'下发失败'},
	       {value:'DEPLOYED', display:EwayLocale.version.taskStatus.deployed}, //'正在部署'},
	       {value:'DEPLOYED_WAIT', display:EwayLocale.version.taskStatus.deployedWait}, //'正在部署'},
	       {value:'DEPLOYED_FAIL', display:EwayLocale.version.taskStatus.deployedFail}, //'部署失败'},
	       {value:'CHECKED', display:EwayLocale.version.taskStatus.checked}, //'部署已确认'},
	       {value:'NOTICE_APP_OK', display:EwayLocale.version.taskStatus.noticeOk}, //'已通知应用'},
	       {value:'NOTICE_APP_FAIL', display:EwayLocale.version.taskStatus.noticeFail}] //'通知应用失败'}]

});
