Ext.define('Eway.store.version.ComboVersionType', {
	extend: 'Eway.store.base.Store',
	fields: ['id', 'typeName','defaultInstallPath','desc'],
	proxy: {
        type: 'rest',
        url : 'api/version/versionType/combo',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    },
	pageSize: 100,
	autoLoad : false		
});