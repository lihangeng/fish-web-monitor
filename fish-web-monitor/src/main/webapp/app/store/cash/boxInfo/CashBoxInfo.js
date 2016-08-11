
Ext.define('Eway.store.cash.boxInfo.CashBoxInfo',{

	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.cash.boxInfo.CashBoxInfo',
	model : 'Eway.model.cash.boxInfo.CashBoxInfo',
	autoLoad : false,
	initComponent : function(){
		this.callParent(arguments);
	}

});
