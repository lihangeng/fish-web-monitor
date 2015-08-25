
Ext.define('Eway.model.agent.RemoteBrowseScreenCustom', {
	extend : 'Ext.data.Model',
	fields : [ 'id',
	           'monitorType',
	           'fileNameClient',
	           'screenShotTime',
	           'allPath'
	          ],
	proxy : {
		type : 'rest',
		url : 'api/agent/screenshot/custom',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
})