Ext.define('Eway.model.operatingPlan.OpenPlan', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'name', 'createDateTime', 'startDate', 'endDate', 'desc',
			'planType', 'planState', 'openPlanDetailForms','planStateType','deviceCount' ],
	proxy : {
		type : 'rest',
		url : 'api/srcb/plan',
		reader : {
			type : 'json',
			root : 'data'
		},
		wirter : {
			type : 'json'
		}
	},
	hasMany : 'OpenPlanDetail'
});
