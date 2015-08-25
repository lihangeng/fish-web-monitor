
Ext.define('Eway.store.version.VersionDownload', {
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 10,
	model: 'Eway.model.version.VersionDownload',

    autoLoad: false
});