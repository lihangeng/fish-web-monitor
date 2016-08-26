
Ext.define('Eway.store.cash.initPlan.CashInitPlanDevice',{
	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.cash.initPlan.CashInitPlanDevice',
	model : 'Eway.model.cash.initPlan.CashInitPlanDevice',
	autoLoad : false,
	initComponent : function(){
		this.callParent(arguments);
	}
});
