Ext.define('Eway.model.machine.atmHardSoft.Cpu', {
	extend : 'Ext.data.Model',
	fields : [ 'frequency', 'vendor', 'model', 'cacheSize', 'totalCores', 'user',
			'sys', 'idle', 'combined' ],
	proxy : {
		type : 'rest',
		url : 'api/machine/device/softAndHardwareInfo',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});
