Ext.define('Eway.store.version.VersionCatalog', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',

	data: [{value:'APP', display:EwayLocale.version.versionCatalog.app},//'应用程序'}, 
	       {value:'V_AGENT', display:EwayLocale.version.versionCatalog.agent},//'监控代理'}, 
	       {value:'ATM_PARAM', display:EwayLocale.version.versionCatalog.param}]//'应用配置'}]
});
