
Ext.define('Eway.store.person.user.UserTypeDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:'超级用户'}, 
	       {value:'1', display:'普通用户'}]
});