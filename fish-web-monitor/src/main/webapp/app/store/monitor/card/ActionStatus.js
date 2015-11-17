Ext.define('Eway.store.monitor.card.ActionStatus', {
			extend : 'Ext.data.Store',

			model : 'Eway.model.Dict',

			data : [{
						value : '',
						display : EwayLocale.commen.all
					}, {
						value : '0',
						display : EwayLocale.monitor.business.card.comboxStatus.wait
					}, {
						value : '3',
						display : EwayLocale.monitor.business.card.comboxStatus.bringed
					}]
		});
