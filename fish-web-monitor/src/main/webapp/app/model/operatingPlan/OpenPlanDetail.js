Ext.define('Eway.model.operatingPlan.OpenPlanDetail', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'week', 'openClose', 'startTime', 'endTime'],
	proxy : {
		type : 'rest',
		url : 'api/srcb/plan/details',
		reader : {
			type : 'json',
			root : 'data'
		},
        wirter : {
        	type : 'json'
        }
	},
	belongsTo : 'OpenPlan'
});