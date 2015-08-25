
Ext.define('Eway.store.case.Fault',{

	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.case.Fault',
	model : 'Eway.model.case.Fault',
	autoLoad : false,
	initComponent : function(){
		this.callParent(arguments);
	}

});