Ext.define('Eway.store.version.VersionCatalog', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',

	data: [{value:'APP', display:Eway.locale.version.versionCatalog.app},//'应用程序'}, 
	       {value:'V_AGENT', display:Eway.locale.version.versionCatalog.agent},//'监控代理'}, 
	       {value:'ATM_PARAM', display:Eway.locale.version.versionCatalog.param}]//'应用配置'}]
});
