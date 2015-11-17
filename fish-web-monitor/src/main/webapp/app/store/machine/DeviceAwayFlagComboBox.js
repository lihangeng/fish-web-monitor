Ext.define('Eway.store.machine.DeviceAwayFlagComboBox', {
	extend : 'Ext.data.Store',

	model : 'Eway.model.Dict',

	data : [ {
		value : '1',
		display : EwayLocale.machine.device.inBank,
	}, {
		value : '2',
		display : EwayLocale.machine.device.outBank,
	}, {
		value : '3',
		display : EwayLocale.machine.device.clickBank,
	} ]
});
