Ext.define('Eway.store.version.JobPriority', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'GENERAL', display:Eway.locale.version.jobPriority.general},//'普通'}, 
	       {value:'MIDDLE', display:Eway.locale.version.jobPriority.middle},//'中等'}, 
	       {value:'HIGH', display:Eway.locale.version.jobPriority.hight}]//'高'}]
});
