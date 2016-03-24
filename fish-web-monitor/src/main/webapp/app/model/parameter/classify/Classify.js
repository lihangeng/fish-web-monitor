Ext.define('Eway.model.parameter.classify.Classify', {
	extend: 'Ext.data.Model',
	fields: ['id', 'name','remark'],
    proxy: {
        type: 'rest',
        url : 'api/parameter/classify',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});