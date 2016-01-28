
Ext.define('Eway.store.bsAdvert.BsAdvertGroup',{

	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.bsAdvert.BsAdvertGroup',
	model : 'Eway.model.bsAdvert.BsAdvertGroup',
	autoLoad : true,
	initComponent : function(){
		this.callParent(arguments);
	}

});