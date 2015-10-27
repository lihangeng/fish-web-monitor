
Ext.define('Eway.store.operatingPlan.OpenPlanForDevice', {
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 10,
	model: 'Eway.model.operatingPlan.OpenPlanForDevice',
	
    autoLoad: false
});