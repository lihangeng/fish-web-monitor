Ext.define('Eway.store.monitor.card.ActionStatus', {
			extend : 'Ext.data.Store',

			model : 'Eway.model.Dict',

			data : [{
						value : '',
						display : Eway.locale.commen.all
					}, {
						value : '0',
						display : Eway.locale.monitor.business.card.comboxStatus.wait
					}, {
						value : '3',
						display : Eway.locale.monitor.business.card.comboxStatus.bringed
					}]
		});
