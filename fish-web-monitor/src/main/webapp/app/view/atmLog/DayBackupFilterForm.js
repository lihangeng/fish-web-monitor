Ext.define('Eway.view.atmLog.DayBackupFilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.atmLog_DayBackupFilterForm',
	
	layout : 'column',
	
	initComponent : function(){
		Ext.apply(this,{
			
			items : [{
				columnWidth : .4,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : Eway.locale.atmLog.backupDate,
					name : 'date',
					editable:false,
					format : 'Y-m-d'
				},{
					xtype : 'combo',
					fieldLabel : Eway.locale.atmLog.dayBackupResult,
					editable : false,
					name : 'dayBackupResult',
					store : Ext.create('Ext.data.Store',{
						fields : ['dayBackupResult','displayField'],
						data : [
							{'dayBackupResult':'','displayField':Eway.locale.atmLog.whole},
							{'dayBackupResult':'SUCCESS','displayField':Eway.locale.tip.success},
							{'dayBackupResult':'DOING','displayField':Eway.locale.atmLog.backupProcess},
							{'dayBackupResult':'ERROR','displayField':Eway.locale.atmLog.unKnownFail}
						]
					}),
					queryMode : 'local',
					valueField : 'dayBackupResult',
					displayField : 'displayField',
					hiddenName : 'dayBackupResult',
					emptyText: Eway.locale.combox.select
				}]
			},{
				columnWidth : .4,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : Eway.locale.version.task.actionTime,
					editable:false,
					name : 'doTime',
					format : 'Y-m-d'
				},{
					xtype : 'datefield',
					fieldLabel : Eway.locale.commen.endDataTime,
					editable:false,
					name : 'endTime',
					format : 'Y-m-d'
				}]
			}]
		});
		this.callParent(arguments);	
	}
	
});