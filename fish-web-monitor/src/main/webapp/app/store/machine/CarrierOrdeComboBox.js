Ext.define('Eway.store.machine.CarrierOrdeComboBox', {
	extend : 'Ext.data.Store',

	model : 'Eway.model.Dict',

	data : [ {
		value : '1',
		display : '升序'
	}, {
		value : '2',
		display : '降序'
	}]
});
