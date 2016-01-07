Ext.define('Eway.model.monitor.device.DeviceProperty', {
	extend : 'Ext.data.Model',
	fields : [ 'cdm', 'cim', 'idc', 'jpr', 'pin', 'rpr', 'ttu', 'siu','cam','bcr','nfc',
			'properties' ],
	proxy : {
		type : 'rest',
		url : 'api/monitor/device/initProperty',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});