
Ext.define('Eway.model.parameter.template.TemplateDetailList', {
	extend : 'Ext.data.Model',
	idProperty : 'elementId',
	fields : [ 'id','elementId', 'paramName', 'paramValue'],
	belongsTo : 'Eway.model.parameter.template.Template'
});
