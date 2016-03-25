
Ext.define('Eway.model.parameter.template.TemplateDevice', {
	extend: 'Ext.data.Model',
	fields: ['id','templateId', 'deviceId'],
    proxy: {
        type: 'rest',
        url : 'api/parameter/template/link',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});
