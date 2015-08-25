Ext.define('Eway.model.machine.atmHardSoft.Disk', {
	extend : 'Ext.data.Model',
	fields : [ 'label', 'name', 'totalSize', 'freeSize', 'fileSys', 'memo',
			'labelAndname' ],
	proxy : {
		type : 'rest',
		url : 'api/machine/device/softAndHardwareInfo',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});
