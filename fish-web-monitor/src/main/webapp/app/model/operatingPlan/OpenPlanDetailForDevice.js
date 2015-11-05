Ext.define('Eway.model.operatingPlan.OpenPlanDetailForDevice', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'week', 'openClose', 'startTime', 'endTime'],
	proxy : {
		type : 'rest',
		url : 'api/plan/detailsForDevice',
		reader : {
			type : 'json',
			rootProperty : 'data'
		},
        wirter : {
        	type : 'json'
        }
	},
	belongsTo : 'OpenPlan'
});