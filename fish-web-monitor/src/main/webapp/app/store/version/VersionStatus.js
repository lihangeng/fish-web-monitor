Ext.define('Eway.store.version.VersionStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
	       {value:'NEW', display:EwayLocale.version.View.newCreate},//新建
	       {value:'WAITING', display:EwayLocale.version.View.waitting},//'等待下发'
	       {value:'DOWNLOADED', display:EwayLocale.version.View.downLoaded}//'已下发'
	      ]
});
