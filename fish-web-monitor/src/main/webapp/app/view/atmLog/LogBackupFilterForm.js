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
					fieldLabel : '设备号',
					name : 'terminalId'
				},{
					xtype : 'datefield',
					fieldLabel : '日志日期',
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
					fieldLabel : '最后一次备份时间',
					name : 'lastDoDate',
					format : 'Y-m-d',
					editable:false,
					labelWidth: 110
				},{
					xtype : 'combo',
					fieldLabel : '备份结果',
					editable : false,
					name : 'backupResult',
					labelWidth: 110,
					store : Ext.create('Ext.data.Store',{
						fields : ['backupResult','displayField'],
						data : [
							{'backupResult':'SUCCESS','displayField':'成功'},
							{'backupResult':'UNDO','displayField':'未开始'},
							{'backupResult':'ERROR_CONNECT','displayField':'连接失败'},
							{'backupResult':'ERROR_NOLOG','displayField':'无日志'},
							{'backupResult':'ERROR','displayField':'未知原因失败'}
						]
					}),
					queryMode : 'local',
					valueField : 'backupResult',
					displayField : 'displayField',
					hiddenName : 'backupResult',
					emptyText: '--请选择--'
				}]
			}]
		});
		this.callParent(arguments);	
	}
	
});