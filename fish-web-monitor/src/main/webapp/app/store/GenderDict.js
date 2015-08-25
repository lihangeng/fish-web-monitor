
Ext.define('Eway.store.GenderDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'MALE', display:'男'}, 
	       {value:'FEMALE', display:'女'}]
});
