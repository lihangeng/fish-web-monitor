Ext.define('Eway.store.version.VersionStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
	       {value:'NEW', display:Eway.locale.commen.stateDict.newCreate},
	       {value:'WAITING', display:'等待下发'},
	       {value:'DOWNLOADED', display:'已下发'}
	      ]
});
