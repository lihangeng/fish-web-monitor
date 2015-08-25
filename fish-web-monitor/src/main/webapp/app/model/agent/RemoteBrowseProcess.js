
Ext.define('Eway.model.agent.RemoteBrowseProcess', {
	extend : 'Ext.data.Model',
	fields : [ 'id',
	           'name',
	           'user',
	           'cpuRate',
	           'memoryRate',
	           'description'
	          ],
	proxy : {
		type : 'rest',
		url : 'api/agent/process',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
})