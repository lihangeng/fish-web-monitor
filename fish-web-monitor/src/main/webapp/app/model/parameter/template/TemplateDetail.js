
Ext.define('Eway.model.parameter.template.TemplateDetail', {
	extend: 'Ext.data.Model',
	fields: ['id', 'paramName','paramValue','templateId','paramBelongs','elementId','resources','name','remark'],
    proxy: {
        type: 'rest',
        url : 'api/parameter/template/templateDetail',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    },
    hasMany: 'TemplateResource'
});