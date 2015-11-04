Ext.define('Eway.store.machine.DeviceStatusComboBox', {
	extend : 'Ext.data.Store',

	model : 'Eway.model.Dict',

	data : [ {
		value : '2',
		display : Eway.locale.commen.comboxStatus.dredge
	}, {
		value : '3',
		display : Eway.locale.commen.comboxStatus.close
	}]
});
