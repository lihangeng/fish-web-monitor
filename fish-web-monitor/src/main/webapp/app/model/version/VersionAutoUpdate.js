/**
 * 版本的模型定义
 */
Ext.define('Eway.model.version.VersionAutoUpdate', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'versionType','versionTypeId','terminalId','deviceIp',{name:'taskStatus',defaultValue: 'NEW'},
	         'version','excuteTime','success','reason','desc','state','orgName','orgId'
	         ,'jobName','planTime','excuteMachine','downSource','atmTypeId','atmType',
	         'versionBeforeUpdate','exceptVersion'],
    proxy: {
        type: 'rest',
        url : 'api/version/versionAutoUpdate',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    }
});