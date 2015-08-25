
Ext.define('Eway.store.atmLog.DayBackup',{

	extend: 'Eway.store.base.Store',
	requires : 'Eway.model.atmLog.DayBackup',
	model : 'Eway.model.atmLog.DayBackup',
	
	initComponent : function(){
		this.callParent(arguments);
	}

});