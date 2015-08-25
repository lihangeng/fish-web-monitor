Ext.define('Eway.store.version.AutoUpdate', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
	       {value:'true', display:'是'},
	       {value:'false', display:'否'}]
});
