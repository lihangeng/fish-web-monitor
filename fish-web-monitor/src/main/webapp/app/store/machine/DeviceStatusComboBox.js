Ext.define('Eway.store.machine.DeviceStatusComboBox', {
	extend : 'Ext.data.Store',

	model : 'Eway.model.Dict',

	data : [ {
		value : '2',
		display : EwayLocale.commen.comboxStatus.dredge
	}, {
		value : '3',
		display : EwayLocale.commen.comboxStatus.close
	}]
});
