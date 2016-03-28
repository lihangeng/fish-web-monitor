
Ext.define('Eway.model.parameter.template.TemplateParam', {
	extend: 'Ext.data.Model',
	fields: ['id','paramId', 'templateId'],
    proxy: {
        type: 'rest',
        url : 'api/parameter/template/addParam',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});