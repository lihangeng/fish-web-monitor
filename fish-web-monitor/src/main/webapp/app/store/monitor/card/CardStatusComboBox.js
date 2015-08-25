Ext.define('Eway.store.monitor.card.CardStatusComboBox', {
	extend : 'Ext.data.Store',
	model : 'Eway.model.Dict',
	data : [ {
		value : '0',
		display : '待领'
	}, {
		value : '1',
		display : '已领'
	}, {
		value : '2',
		display : '销毁'
	}, {
		value : '3',
		display : '调出'
	} ]
});
