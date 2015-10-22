Ext.define('Eway.view.atmLog.LogBackup',{

	extend : 'Eway.view.base.Panel',
	alias : 'widget.atmLog_LogBackup',
	
	requires : [
		'Eway.view.atmLog.LogBackupGrid',
		'Eway.view.atmLog.LogBackupFilterForm'
	],
	
	layout : 'border',
	
	initComponent : function(){
		
		Ext.apply(this,{
			title : Eway.locale.atmLog.appLogDownload,
			
			items : [{
				region : 'north',
				xtype : 'atmLog_LogBackupFilterForm',
				height : 70
			},{
				region : 'center',
				xtype : 'atmLog_LogBackupGrid'
			}]
		});
		this.callParent(arguments);
	}
	
});