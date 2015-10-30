Ext.define('Eway.model.operatingPlan.OpenPlanForDevice', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'name', 'createDateTime', 'startDate', 'endDate', 'desc',
			'planType', 'planState', 'openPlanDetailForms','deviceId','terminalId','planStateType'],
	proxy : {
		type : 'rest',
		url : 'api/srcb/plan/device',
		reader : {
			type : 'json',
			root : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
