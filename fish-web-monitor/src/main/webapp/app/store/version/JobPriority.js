Ext.define('Eway.store.version.JobPriority', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'GENERAL', display:EwayLocale.version.jobPriority.general},//'普通'}, 
	       {value:'MIDDLE', display:EwayLocale.version.jobPriority.middle},//'中等'}, 
	       {value:'HIGH', display:EwayLocale.version.jobPriority.hight}]//'高'}]
});
