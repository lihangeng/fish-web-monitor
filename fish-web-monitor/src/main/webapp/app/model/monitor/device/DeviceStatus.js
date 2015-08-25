Ext.define('Eway.model.monitor.device.DeviceStatus', {
	extend : 'Ext.data.Model',
	fields : [ 'cdm', 'cim', 'idc', 'jpr', 'pin', 'rpr', 'ttu', 'siu' ],
	proxy : {
		type : 'rest',
		url : 'api/machine/device/deviceModuleStatusForm/initStatus',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});