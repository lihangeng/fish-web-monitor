
Ext.define('Eway.store.person.person.GenderFilterDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'', display:'全部'}, 
		   {value:'MALE', display:'男'}, 
	       {value:'FEMALE', display:'女'}]
});
