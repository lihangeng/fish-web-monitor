
Ext.define('Eway.store.person.user.UserStateDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:'新建'}, 
	       {value:'1', display:'正常'}, 
	       {value:'2', display:'锁定'}, 
	       {value:'4', display:'冻结'},
	       {value:'3', display:'已删除'}]
});
