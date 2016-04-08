Ext.define('Eway.model.parameter.template.Template', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'name', 'remark', 'paramBelongsId', 'paramBelongsName','applyFlag', 'resources' ],
			
	proxy : {
		type : 'rest',
		url : 'api/parameter/template',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
//,
//	hasMany : 'TemplateDetailList'
});