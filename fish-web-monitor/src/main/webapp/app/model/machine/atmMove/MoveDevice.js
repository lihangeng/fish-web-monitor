Ext.define('Eway.model.machine.atmMove.MoveDevice', {
	extend : 'Ext.data.Model',
	fields : ['id', 'terminalId', 'orgId', 'orgName', 'address' ],

	proxy : {
		type : 'rest',
		url : 'api/machine/device',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});