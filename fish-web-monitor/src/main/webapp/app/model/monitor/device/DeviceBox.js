Ext.define('Eway.model.monitor.device.DeviceBox', {
	extend : 'Ext.data.Model',
	fields : [ 'termId', 'dispenseAmount',
	           {
		name : 'initAmount',
		type : 'number'
	}, {
		name : 'amount',
		type : 'number'
	},
			'rejectAmount', 'retractCount', 'minAmount', 'ret' ],
	proxy : {
		type : 'ajax',
		url : 'api/agent/boxdetail/deviceBox',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	hasMany : {
		model : 'CashBox',
		name : 'boxList'
	}
});
Ext.define('CashBox', {
	extend : 'Ext.data.Model',
	idProperty : 'boxId',
	fields : [ 'boxId', 'deviceBox_id', 'type','typeName', 'binStatus','binStatusName', 'initialCount',
			'currentCount', 'noteValue', 'currency', 'cashInCount' ],
	belongsTo : 'Eway.model.monitor.device.DeviceBox'
});