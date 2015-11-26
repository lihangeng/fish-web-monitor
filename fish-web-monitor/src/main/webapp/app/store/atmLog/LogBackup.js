
Ext.define('Eway.store.atmLog.LogBackup',{

	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.atmLog.LogBackup',
	model : 'Eway.model.atmLog.LogBackup',
	pageSize: 20,
	autoLoad : false,
	initComponent : function(){
		this.callParent(arguments);
	}

});