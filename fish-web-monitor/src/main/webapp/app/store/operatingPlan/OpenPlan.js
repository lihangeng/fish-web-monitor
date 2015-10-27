Ext.define('Eway.store.operatingPlan.OpenPlan', {
	extend : 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	autoLoad : true,
	model : 'Eway.model.operatingPlan.OpenPlan'
});