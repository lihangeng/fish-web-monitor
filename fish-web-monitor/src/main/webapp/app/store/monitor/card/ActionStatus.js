Ext.define('Eway.store.monitor.card.ActionStatus', {
			extend : 'Ext.data.Store',

			model : 'Eway.model.Dict',

			data : [{
						value : '',
						display : '全部'
					}, {
						value : '0',
						display : '待领'
					}, {
						value : '3',
						display : '调出'
					}]
		});
