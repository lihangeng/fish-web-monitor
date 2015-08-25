Ext.define('Eway.controller.atmLog.LogBackup',{

	extend : 'Eway.controller.base.FishController',
	
	stores : [],
	models : [],
	views : ['atmLog.LogBackup'],
	
	refs : [{
		ref : 'ewayView',
		selector : '#atmLog_LogBackup',
		autoCreate : true,
		xtype : 'atmLog_LogBackup'
	}],
	
	init : function(){
		this.control({
			'atmLog_LogBackup > atmLog_LogBackupGrid button[action="query"]' : {
				'click' : this.onQuery
			}
		});
		this.onQuery();
	}
});