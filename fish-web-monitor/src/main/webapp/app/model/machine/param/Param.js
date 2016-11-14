
Ext.define('Eway.model.machine.param.Param', {
	extend: 'Ext.data.Model',
	fields: ['id','paramType', 'paramKey','paramValue','classify','paramDisplayValue','description'],
    proxy: {
        type: 'rest',
        url : 'api/machine/param',
        reader: { 
            type: 'json',
            rootProperty: 'data'
        }
    }
});