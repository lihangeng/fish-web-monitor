Ext.define('Eway.store.machine.DeviceAwayFlagComboBox', {
	extend : 'Ext.data.Store',

	model : 'Eway.model.Dict',

	data : [ {
		value : '1',
		display : Eway.locale.machine.device.inBank,
	}, {
		value : '2',
		display : Eway.locale.machine.device.outBank,
	}, {
		value : '3',
		display : Eway.locale.machine.device.clickBank,
	} ]
});
