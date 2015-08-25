
Ext.define('Eway.store.atmLog.AtmLogInfo',{

	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.atmLog.AtmLogInfo',
	model : 'Eway.model.atmLog.AtmLogInfo',
	
	initComponent : function(){
		this.callParent(arguments);
	}

});