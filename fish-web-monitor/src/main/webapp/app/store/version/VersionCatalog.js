Ext.define('Eway.store.version.VersionCatalog', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'APP', display:'应用程序'}, 
	       {value:'V_AGENT', display:'监控代理'}, 
	       {value:'ATM_PARAM', display:'应用配置'}]
});
