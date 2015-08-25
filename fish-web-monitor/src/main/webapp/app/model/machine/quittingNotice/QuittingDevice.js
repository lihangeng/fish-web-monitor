
Ext.define('Eway.model.machine.quittingNotice.QuittingDevice', {
	extend: 'Ext.data.Model',
	fields: ['id', 'terminalId','orgName','address'],

	proxy: {
	    type: 'rest',
	    url : 'api/machine/device',
	    reader: {
	        type: 'json',
	        rootProperty: 'data'
	    }
	}
});