Ext.define('Eway.store.version.VersionStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
	       {value:'NEW', display:'新建'},
	       {value:'WAITING', display:'等待下发'},
	       {value:'DOWNLOADED', display:'已下发'}
	      ]
});
