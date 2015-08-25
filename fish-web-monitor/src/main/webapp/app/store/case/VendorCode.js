Ext.define('Eway.store.case.VendorCode',{

	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.case.VendorCode',
	model : 'Eway.model.case.VendorCode',
	
	initComponent : function(){
		this.callParent(arguments);
	}

});