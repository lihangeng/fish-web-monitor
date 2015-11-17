Ext.define('Eway.store.monitor.card.CardStatusComboBox', {
	extend : 'Ext.data.Store',
	model : 'Eway.model.Dict',
	data : [ {
		value : '0',
		display : EwayLocale.monitor.business.card.comboxStatus.wait
	}, {
		value : '1',
		display : EwayLocale.monitor.business.card.comboxStatus.received
	}, {
		value : '2',
		display : EwayLocale.monitor.business.card.comboxStatus.destroy
	}, {
		value : '3',
		display : EwayLocale.monitor.business.card.comboxStatus.bringed
	} ]
});
