
Ext.define('Eway.store.atmLog.LogBackup',{

	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.atmLog.LogBackup',
	model : 'Eway.model.atmLog.LogBackup',
	autoLoad : false,
	initComponent : function(){
		this.callParent(arguments);
	}

});