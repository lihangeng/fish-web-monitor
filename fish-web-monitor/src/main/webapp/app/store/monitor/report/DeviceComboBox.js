Ext.define('Eway.store.monitor.report.DeviceComboBox', {
	extend : 'Ext.data.Store',
	model : 'Eway.model.Dict',
	data : [ {
		value : '0',
		display : Eway.locale.monitor.business.card.withDev
	} ]
});
