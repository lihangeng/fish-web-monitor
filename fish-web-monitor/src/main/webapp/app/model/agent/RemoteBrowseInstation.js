
Ext.define('Eway.model.agent.RemoteBrowseInstation', {
	extend : 'Ext.data.Model',
	fields : [ 'id',
	           'programName',
	           'version',
	           'publisher',
	           'installDate',
	           'diskUsed'
	          ],
	proxy : {
		type : 'rest',
		url : 'api/agent/instation',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
})