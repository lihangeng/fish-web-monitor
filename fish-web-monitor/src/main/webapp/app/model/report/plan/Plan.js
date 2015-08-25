Ext.define('Eway.model.report.plan.Plan', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'name', 'startDate', 'endDate', 'note' ],
	proxy : {
		type : 'rest',
		url : 'api/report/plan',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});