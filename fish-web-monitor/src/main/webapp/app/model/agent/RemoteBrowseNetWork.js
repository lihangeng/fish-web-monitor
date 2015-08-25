
Ext.define('Eway.model.agent.RemoteBrowseNetWork', {
	extend : 'Ext.data.Model',
	fields : [ 'id',
	           'conenctRate',
	           'receivedByte',
	           'sendByte'
	          ],
	proxy : {
		type : 'rest',
		url : 'api/agent/netWork',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
})