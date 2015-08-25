Ext.define('Eway.controller.atmLog.DayBackup',{

	extend : 'Eway.controller.base.FishController',
	
	stores : [],
	models : [],
	views : ['atmLog.DayBackup'],
	
	refs : [{
		ref : 'ewayView',
		selector : '#atmLog_DayBackup',
		autoCreate : true,
		xtype : 'atmLog_DayBackup'
	}],
	
	init : function(){
		this.control({
			'atmLog_DayBackup > atmLog_DayBackupGrid button[action="query"]' : {
				'click' : this.onQuery
			}
		});
	}
});