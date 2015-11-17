Ext.define('Eway.view.atmLog.errorAtmLogInfo.ErrorAtmLogInfoFilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.atmLog_errorAtmLogInfo_ErrorAtmLogInfoFilterForm',
	
	layout : 'column',
	
	initComponent : function(){
		Ext.apply(this,{
			
			items : [{
				columnWidth : .4,
				xtype : 'form',
				items : [/*{
					xtype : 'datefield',
					fieldLabel : '备份日期',
					name : 'date',
					format : 'y-m-d'
				},*/{
					xtype : 'combo',
					fieldLabel : EwayLocale.atmLog.dayBackup,
					name : 'dayBackupResult',
					store : Ext.create('Ext.data.Store',{
						fields : ['dayBackupResult','displayField'],
						data : [
							{'dayBackupResult':'','displayField':EwayLocale.atmLog.whole},
							{'dayBackupResult':'SUCCESS','displayField':EwayLocale.atmLog.success},
							{'dayBackupResult':'DOING','displayField':EwayLocale.atmLog.execute},
							{'dayBackupResult':'ERROR','displayField':EwayLocale.atmLog.unKnownFail}
						]
					}),
					queryMode : 'local',
					valueField : 'dayBackupResult',
					displayField : 'displayField',
					hiddenName : 'dayBackupResult'
				}]
			}/*,{
				columnWidth : .4,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : '执行时间',
					name : 'doTime',
					format : 'y-m-d'
				},{
					xtype : 'datefield',
					fieldLabel : '结束时间',
					name : 'endTime',
					format : 'y-m-d'
				}]
			}*/]
		});
		this.callParent(arguments);	
	}
	
});