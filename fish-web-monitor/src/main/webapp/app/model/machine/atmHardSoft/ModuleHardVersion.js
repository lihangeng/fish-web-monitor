Ext.define('Eway.model.machine.atmHardSoft.ModuleHardVersion', {
	extend : 'Ext.data.Model',
	// fields : [ 'name', 'enName', 'spVersion', 'driverVersion', 'fwVersion' ],
	fields : [ 'listHal' ],
	proxy : {
		type : 'rest',
		url : 'api/machine/device/softhardware/initHardware',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});
