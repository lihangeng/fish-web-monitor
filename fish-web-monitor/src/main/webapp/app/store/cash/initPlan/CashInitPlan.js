
Ext.define('Eway.store.cash.initPlan.CashInitPlan',{
	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.cash.initPlan.CashInitPlan',
	model : 'Eway.model.cash.initPlan.CashInitPlan',
	autoLoad : false,
	initComponent : function(){
		this.callParent(arguments);
	}
});
