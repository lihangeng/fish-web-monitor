
Ext.define('Eway.model.machine.quittingNotice.DeviceCode', {
	extend: 'Ext.data.Model',
		fields : [ {
			name : 'id'
		}, {name : 'terminalId'}],
		proxy : {
			type : 'rest',
			url : 'api/machine/device/findMatch',
			reader : {
				type : 'json',
                totalProperty: 'totalCount',
				rootProperty : 'data'
			}
		}
	});