
Ext.define('Eway.store.person.user.UserStateFilterDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
		   {value:'0', display:'新建'}, 
	       {value:'1', display:'正常'},
	       {value:'2', display:'锁定'}]
});
