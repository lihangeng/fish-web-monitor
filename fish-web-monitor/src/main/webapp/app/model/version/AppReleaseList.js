Ext.define('Eway.model.version.AppReleaseList', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id','versionNo','dependVersion','fullName'],
});