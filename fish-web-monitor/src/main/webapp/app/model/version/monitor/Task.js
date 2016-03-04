Ext.define('Eway.model.version.monitor.Task', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: [{name:'id',type:'int'}, {name:'deviceId',type:'int'},'terminalId','deviceIp',{name:'taskStatus',defaultValue: 'NEW'},
	         {name:'jobId',type:'int'},'version','excuteTime','success','reason','desc','state','orgName',
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