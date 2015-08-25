Ext.define('Eway.model.version.Task', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'deviceId','terminalId','deviceIp',{name:'taskStatus',defaultValue: 'NEW'},
	         'jobId','version','excuteTime','success','reason','desc','state','orgName'
	         ,'jobName','planTime','excuteMachine','downSource',
	         'versionBeforeUpdate','currentVersion','exceptVersion'],
    proxy: {
        type: 'rest',
        url : 'api/version/download/task',
        reader: {
            type: 'json',
            rootProperty : 'data'
        },
        wirter : {
        	type : 'json'
        }
    }
});