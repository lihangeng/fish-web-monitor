Ext.define('Eway.store.machine.DeviceAwayFlagComboBox', {
	extend : 'Ext.data.Store',

	model : 'Eway.model.Dict',

	data : [ {
		value : '1',
		display : '在行自助服务区'
	}, {
		value : '2',
		display : '离行自助银行'
	}, {
		value : '3',
		display : '单机离行自助服务点'
	} ]
});
