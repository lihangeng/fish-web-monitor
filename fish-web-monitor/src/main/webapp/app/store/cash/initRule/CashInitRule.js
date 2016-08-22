
Ext.define('Eway.store.cash.initRule.CashInitRule',{
	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.cash.initRule.CashInitRule',
	model : 'Eway.model.cash.initRule.CashInitRule',
	autoLoad : false,
	initComponent : function(){
		this.callParent(arguments);
	}
});
