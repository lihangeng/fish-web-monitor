Ext.define('Eway.view.atmLog.DayBackup',{

	extend : 'Eway.view.base.Panel',
	alias : 'widget.atmLog_DayBackup',
	
	requires : [
		'Eway.view.atmLog.DayBackupGrid',
		'Eway.view.atmLog.DayBackupFilterForm'
	],
	
	layout : 'border',
	
	initComponent : function(){
		
		Ext.apply(this,{
			title : Eway.locale.atmLog.dailyBackup,
			items : [{
				region : 'north',
				xtype : 'atmLog_DayBackupFilterForm',
				height : 70
			},{
				region : 'center',
				xtype : 'atmLog_DayBackupGrid'
			}]
		});
		this.callParent(arguments);
	}
	
});