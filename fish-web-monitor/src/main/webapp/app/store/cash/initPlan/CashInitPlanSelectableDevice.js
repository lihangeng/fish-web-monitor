
Ext.define('Eway.store.cash.initPlan.CashInitPlanSelectableDevice',{
	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.cash.initPlan.CashInitPlanSelectableDevice',
	model : 'Eway.model.cash.initPlan.CashInitPlanSelectableDevice',
	autoLoad : false,
	initComponent : function(){
		this.callParent(arguments);
	}
});
