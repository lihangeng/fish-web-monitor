Ext.define('Eway.model.machine.atmGroup.DeviceGroup', {
	extend : 'Ext.data.Model',
	fields : ['id','name', 'note'],
	proxy : {
		type : 'rest',
		url : 'api/machine/atmGroup',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});