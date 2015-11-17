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
					fieldLabel : EwayLocale.commen.terminalId,
					name : 'terminalId'
				},{
					xtype : 'datefield',
					fieldLabel : EwayLocale.atmLog.logDate,
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
					fieldLabel : EwayLocale.atmLog.lastBackupTime,
					name : 'lastDoDate',
					format : 'Y-m-d',
					editable:false,
					labelWidth: 110
				},{
					xtype : 'combo',
					fieldLabel : EwayLocale.atmLog.backupResult,
					editable : false,
					name : 'backupResult',
					labelWidth: 110,
					store : Ext.create('Ext.data.Store',{
						fields : ['backupResult','displayField'],
						data : [
							{'backupResult':'SUCCESS','displayField':EwayLocale.tip.success},
							{'backupResult':'UNDO','displayField':EwayLocale.atmLog.noBegin},
							{'backupResult':'ERROR_CONNECT','displayField':EwayLocale.tip.linkFailure},
							{'backupResult':'ERROR_NOLOG','displayField':EwayLocale.atmLog.noLog},
							{'backupResult':'ERROR','displayField':EwayLocale.atmLog.unKnownFail}
						]
					}),
					queryMode : 'local',
					valueField : 'backupResult',
					displayField : 'displayField',
					hiddenName : 'backupResult',
					emptyText: EwayLocale.combox.select
				}]
			}]
		});
		this.callParent(arguments);	
	}
	
});