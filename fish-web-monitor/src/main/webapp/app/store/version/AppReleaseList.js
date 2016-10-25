Ext.define('Eway.store.version.AppReleaseList', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.version.AppReleaseList',
	autoSync : false,
	pageSize: 500,
    autoLoad: false,
    groupField: 'versionType'
});