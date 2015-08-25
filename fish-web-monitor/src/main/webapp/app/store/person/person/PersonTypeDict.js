
Ext.define('Eway.store.person.person.PersonTypeDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:'管机员'}, 
	       {value:'1', display:'维修人员'}]
});