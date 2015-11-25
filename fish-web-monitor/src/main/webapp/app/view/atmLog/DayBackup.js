Ext.define('Eway.view.atmLog.DayBackup',{

	extend : 'Eway.view.base.Panel',
	alias : 'widget.atmLog_DayBackup',
	
	requires : [
		'Eway.view.atmLog.DayBackupGrid',
		'Eway.view.atmLog.DayBackupFilterForm'
	],	
	scrollable : true,
	autoScroll : true,
	title : EwayLocale.atmLog.title,
	initComponent : function(){		
		Ext.apply(this,{			
			xtype : 'panel',
			items : [{
				region : 'north',
				xtype : 'atmLog_DayBackupFilterForm',
				height : 46
			},{
				region : 'center',
				xtype : 'atmLog_DayBackupGrid',
				height : 300
			},{
				xtype:'panel',
				region : 'south',
				name :'backupDetail',
				height : 620
			}]
		});
		this.callParent(arguments);
	}
	
});