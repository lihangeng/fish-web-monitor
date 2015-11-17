
Ext.define('Eway.store.monitor.transaction.BlacklistDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [ 
			{value:'1', display:EwayLocale.commen.yes}, 
	       {value:'2', display:EwayLocale.commen.no}]
});