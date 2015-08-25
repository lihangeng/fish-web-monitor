
Ext.define('Eway.store.monitor.transaction.BlacklistDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [ 
			{value:'1', display:'是'}, 
	       {value:'2', display:'否'}]
});