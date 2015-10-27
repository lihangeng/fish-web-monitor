Ext.define("Eway.store.operatingPlan.TempOpenPlanForDevice",{
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 10,
	model: 'Eway.model.operatingPlan.TempOpenPlanForDevice',
    autoLoad: false
});