
Ext.define('Eway.store.operatingPlan.OpenPlanDetail', {
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 10,
	model: 'Eway.model.operatingPlan.OpenPlanDetail',
	
    autoLoad: false
});