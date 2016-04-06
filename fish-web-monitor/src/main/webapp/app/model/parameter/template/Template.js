
Ext.define('Eway.model.parameter.template.Template', {
	extend: 'Ext.data.Model',
	fields: ['id', 'name','remark','paramBelongsId','paramBelongsName','applyFlag'],
    proxy: {
        type: 'rest',
        url : 'api/parameter/template',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});