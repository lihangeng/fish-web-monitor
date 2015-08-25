Ext.define('Eway.store.version.VersionChartsDetails', {
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 10,
	model: 'Eway.model.version.VersionChartsDetails',
    autoLoad: false
});
