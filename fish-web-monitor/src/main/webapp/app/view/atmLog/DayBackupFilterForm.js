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
					fieldLabel : '备份日期',
					name : 'date',
					editable:false,
					format : 'Y-m-d'
				},{
					xtype : 'combo',
					fieldLabel : '当日备份结果',
					editable : false,
					name : 'dayBackupResult',
					store : Ext.create('Ext.data.Store',{
						fields : ['dayBackupResult','displayField'],
						data : [
							{'dayBackupResult':'','displayField':'所有'},
							{'dayBackupResult':'SUCCESS','displayField':'成功'},
							{'dayBackupResult':'DOING','displayField':'正在备份'},
							{'dayBackupResult':'ERROR','displayField':'未知原因失败'}
						]
					}),
					queryMode : 'local',
					valueField : 'dayBackupResult',
					displayField : 'displayField',
					hiddenName : 'dayBackupResult',
					emptyText: '--请选择--'
				}]
			},{
				columnWidth : .4,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : '执行时间',
					editable:false,
					name : 'doTime',
					format : 'Y-m-d'
				},{
					xtype : 'datefield',
					fieldLabel : '结束时间',
					editable:false,
					name : 'endTime',
					format : 'Y-m-d'
				}]
			}]
		});
		this.callParent(arguments);	
	}
	
});