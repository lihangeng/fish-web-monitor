
Ext.define('Eway.model.parameter.template.TemplateResource', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'templateId' ,'paramName','paramValue'],
	         
    proxy: {
        type: 'rest',
        url : 'api/parameter/template/resource',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    },
    
    belongsTo : 'TemplateDetail'
});