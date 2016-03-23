Ext.define('Eway.model.parameter.classify.Classify', {
	extend: 'Ext.data.Model',
	fields: ['id', 'paramName','paramRemark'],
    proxy: {
        type: 'rest',
        url : 'api/parameter/element',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});