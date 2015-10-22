Ext.define('Eway.view.atmLog.LogBackupFilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.atmLog_LogBackupFilterForm',
	
	layout : 'column',
	
	initComponent : function(){
		Ext.apply(this,{
			
			items : [{
				columnWidth : .4,
				xtype : 'form',
				items : [{
					xtype : 'textfield',
					fieldLabel : Eway.locale.commen.terminalId,
					name : 'terminalId'
				},{
					xtype : 'datefield',
					fieldLabel : Eway.locale.atmLog.logDate,
					value : Ext.Date.add(new Date(), Ext.Date.DAY, -1),
					editable:false,
					name : 'dateTime',
					format : 'Y-m-d'
				}]
			},{
				columnWidth : .4,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : Eway.locale.atmLog.lastBackupTime,
					name : 'lastDoDate',
					format : 'Y-m-d',
					editable:false,
					labelWidth: 110
				},{
					xtype : 'combo',
					fieldLabel : Eway.locale.atmLog.backupResult,
					editable : false,
					name : 'backupResult',
					labelWidth: 110,
					store : Ext.create('Ext.data.Store',{
						fields : ['backupResult','displayField'],
						data : [
							{'backupResult':'SUCCESS','displayField':Eway.locale.tip.success},
							{'backupResult':'UNDO','displayField':Eway.locale.atmLog.noBegin},
							{'backupResult':'ERROR_CONNECT','displayField':Eway.locale.tip.linkFailure},
							{'backupResult':'ERROR_NOLOG','displayField':Eway.locale.atmLog.noLog},
							{'backupResult':'ERROR','displayField':Eway.locale.atmLog.unKnownFail}
						]
					}),
					queryMode : 'local',
					valueField : 'backupResult',
					displayField : 'displayField',
					hiddenName : 'backupResult',
					emptyText: Eway.locale.combox.select
				}]
			}]
		});
		this.callParent(arguments);	
	}
	
});