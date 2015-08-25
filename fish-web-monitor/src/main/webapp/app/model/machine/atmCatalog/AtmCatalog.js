
Ext.define('Eway.model.machine.atmCatalog.AtmCatalog', {
	extend: 'Ext.data.Model',
	fields: ['id', 'no','name','note'],
    proxy: {
        type: 'rest',
        url : 'api/machine/atmCatalog',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});