
Ext.define('Eway.model.machine.quittingNotice.QuittingNotice', {
	extend: 'Ext.data.Model',
	fields: ['id', 'deviceCode',
		{name:'stopTime'},{name:'openTime'},{name:'setTime'},
		'devStatus','stopType','stopReason','responsibilityName'],
		
    idProperty : 'id',
    proxy: {
        type: 'rest',
        url : 'api/machine/quittingNotice',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
		wirter : {
			type : 'json'
		}
    }

});