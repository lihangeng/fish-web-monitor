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
					fieldLabel : '当日备份结果',
					name : 'dayBackupResult',
					store : Ext.create('Ext.data.Store',{
						fields : ['dayBackupResult','displayField'],
						data : [
							{'dayBackupResult':'','displayField':'所有'},
							{'dayBackupResult':'SUCCESS','displayField':'成功'},
							{'dayBackupResult':'DOING','displayField':'执行中'},
							{'dayBackupResult':'ERROR','displayField':'未知原因失败'}
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