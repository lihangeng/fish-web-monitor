Ext.define('Eway.store.version.VersionStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
	       {value:'NEW', display:Eway.locale.version.View.newCreate},//新建
	       {value:'WAITING', display:Eway.locale.version.View.waitting},//'等待下发'
	       {value:'DOWNLOADED', display:Eway.locale.version.View.downLoaded}//'已下发'
	      ]
});
