Ext.define('Eway.model.operatingPlan.OpenPlanDetailForDevice', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'week', 'openClose', 'startTime', 'endTime'],
	proxy : {
		type : 'rest',
		url : 'api/srcb/plan/detailsForDevice',
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