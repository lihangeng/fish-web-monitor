
Ext.define('Eway.model.machine.atmModule.AtmModule', {
	extend: 'Ext.data.Model',
	fields: ['id', 'no','name','note'],
    proxy: {
        type: 'rest',
        url : 'api/machine/atmModule',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});