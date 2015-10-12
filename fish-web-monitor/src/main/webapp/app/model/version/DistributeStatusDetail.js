
Ext.define('Eway.model.version.DistributeStatusDetail', {
	extend: 'Ext.data.Model',
	fields: ['terminalId', 'orgName','deviceType','vendor','beforeUpdateVersionNo',
             'afterUpdateVersionNo','statusText','ip','updateType' ],
    proxy: {
        type: 'rest',
        url: 'api/version/version/distributeStatusDetail',
        reader: {
            type: 'json',
            rootProperty : 'data'
        },
        wirter : {
        	type : 'json'
        }
    }
});