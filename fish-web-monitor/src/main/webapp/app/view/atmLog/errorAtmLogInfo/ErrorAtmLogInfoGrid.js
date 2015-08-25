Ext.define('Eway.view.atmLog.errorAtmLogInfo.ErrorAtmLogInfoGrid',{

	extend : 'Eway.view.base.Grid',
	alias : 'widget.atmLog_errorAtmLogInfo_ErrorAtmLogInfoGrid',
	
	
	initComponent : function(){
		var store = Ext.create('Eway.store.atmLog.ErrorAtmLogInfo');
//		store.loadPage(1);
		Ext.apply(this,{
			store : store,
			columns : [{
				header : 'id',
				dataIndex : 'id',
				flex : 1,
				hidden : true
			},{
				header : '设备号',
				dataIndex : 'terminalId',
				flex : 1
			},{
				header : '日志日期',
				dataIndex : 'dateTime',
				flex : 1
			},{
				header : '备份结果',
				dataIndex : 'backupResult',
				width : 100,
				renderer : function(value,metaData,record,rowIndex,colIndex,store,view){
					if(value == 'SUCCESS'){
						return '成功';
					}else{
						return '失败';
					}
				}
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
			
				store : store,
				displayInfo : true
			}),
			tbar : ['->',{
				xtype : 'button',
				text : '导出',
				iconCls : 'exportToExcel',
				action : 'export'
			}]
		});
		this.callParent(arguments);
	}
	
});