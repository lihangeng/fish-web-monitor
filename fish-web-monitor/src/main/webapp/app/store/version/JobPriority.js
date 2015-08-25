Ext.define('Eway.store.version.JobPriority', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'GENERAL', display:'普通'}, 
	       {value:'MIDDLE', display:'中等'}, 
	       {value:'HIGH', display:'高'}]
});
