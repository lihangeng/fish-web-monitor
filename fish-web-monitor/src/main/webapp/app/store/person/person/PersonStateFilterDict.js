
Ext.define('Eway.store.person.person.PersonStateFilterDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
	       {value:'1', display:'在岗'}, 
	       {value:'2', display:'调休'}, 
	       {value:'3', display:'休假'}, 
	       {value:'0', display:'其他'}]
});