Ext.define('Eway.model.operatingPlan.OpenPlanDetail', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'week', 'openClose', 'startTime', 'endTime'],
	proxy : {
		type : 'rest',
		url : 'api/plan/details',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	belongsTo : 'OpenPlan'
});