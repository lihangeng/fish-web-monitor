Ext.define('Eway.store.machine.DeviceStatusComboBox', {
	extend : 'Ext.data.Store',

	model : 'Eway.model.Dict',

	data : [ {
		value : '2',
		display : '开通'
	}, {
		value : '3',
		display : '停用'
	}]
});
