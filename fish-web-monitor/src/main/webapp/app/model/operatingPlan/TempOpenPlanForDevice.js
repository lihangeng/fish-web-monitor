Ext.define("Eway.model.operatingPlan.TempOpenPlanForDevice",{
	extend : 'Ext.data.Model',
	fields : [ 'id', 'name', 'createDateTime', 'startDate', 'endDate', 'desc',
				'planType', 'planState', 'openPlanDetailForms','deviceId','terminalId','planStateType'],
	proxy : {
		type : 'rest',
		url : 'api/srcb/plan/tempdevopenplan',
		reader : {
			type : 'json',
			root : 'data'
		},writer : {
			type : 'json'
		}
	}
});