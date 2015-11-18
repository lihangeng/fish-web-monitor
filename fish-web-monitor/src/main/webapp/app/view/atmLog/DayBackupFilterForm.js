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
					fieldLabel : EwayLocale.atmLog.backupDate,
					name : 'date',
					editable:false,
					format : 'Y-m-d'
				},{
					xtype : 'combo',
					fieldLabel : EwayLocale.atmLog.dayBackupResult,
					editable : false,
					name : 'dayBackupResult',
					store : Ext.create('Ext.data.Store',{
						fields : ['dayBackupResult','displayField'],
						data : [
							{'dayBackupResult':'','displayField':EwayLocale.atmLog.whole},
							{'dayBackupResult':'SUCCESS','displayField':EwayLocale.tip.success},
							{'dayBackupResult':'DOING','displayField':EwayLocale.atmLog.backupProcess},
							{'dayBackupResult':'ERROR','displayField':EwayLocale.atmLog.unKnownFail}
						]
					}),
					queryMode : 'local',
					valueField : 'dayBackupResult',
					displayField : 'displayField',
					hiddenName : 'dayBackupResult',
					emptyText: EwayLocale.combox.select
				}]
			},{
				columnWidth : .4,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : EwayLocale.version.task.actionTime,
					editable:false,
					name : 'doTime',
					format : 'Y-m-d'
				},{
					xtype : 'datefield',
					fieldLabel : EwayLocale.commen.endDataTime,
					editable:false,
					name : 'endTime',
					format : 'Y-m-d'
				}]
			}]
		});
		this.callParent(arguments);	
	}
	
});