
Ext.define('Eway.model.version.DeviceVersionHistory', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'ip','deviceId','terminalId','versionId','versionType',
			'versionNo','fullName','userName','downTime','status','remark'],
    proxy: {
        type: 'rest',
        url : 'api/version/device/history',
        reader: {
            type: 'json',
            rootProperty : 'data'
        },
        wirter : {
        	type : 'json'
        }
    }
});