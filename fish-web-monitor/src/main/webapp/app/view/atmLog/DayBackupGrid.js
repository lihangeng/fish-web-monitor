Ext.define('Eway.view.atmLog.DayBackupGrid',{

	extend : 'Eway.view.base.Grid',
	alias : 'widget.atmLog_DayBackupGrid',
	
	
	initComponent : function(){
		var store = Ext.create('Eway.store.atmLog.DayBackup');
		store.loadPage(1);
		Ext.apply(this,{
			store : store,
			columns : [{
				header : '备份日期',
				dataIndex : 'date',
				flex : 1
			},{
				header : '当日备份结果',
				dataIndex : 'result',
				flex : 1,
				renderer : function(value){
					if(value == 'SUCCESS'){
						return "备份成功";
					}
					else if(value == "DOING"){
						return "<pre style='color:green'>正在备份</pre>";
					}
					else if(value == "ERROR"){
						return "<pre style='color:red'>备份错误</pre>";
					}
				}
			},{
				header : '执行时间',
				dataIndex : 'doTime',
				flex : 1
			},{
				header : '结束时间',
				dataIndex : 'endTime',
				flex : 1
			},{
				header : '日志设备数量累计',
				dataIndex : 'deviceCount',
				flex : 1
			},{
				xtype:'actioncolumn',
				flex : .5,
				header: '重做',
				dataIndex : 'backupResult',
				items : [{
					icon : 'resources/images/arrow_undo.png',
					tooltip: '重做',
					getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var result = record.get('result'); 
						if(result == 'SUCCESS'){
							return 'hiddenComp';
						}
						else if(result == 'DOING'){
							return 'hiddenComp';
						}
						else if(result == 'ERROR'){
							return 'changeCursor';
						}
					},
					handler : function(grid,rowIndex,colIndex){
						var record = grid.getStore().getAt(rowIndex);
						var date = record.get('date');
						Ext.Ajax.request({
							method : 'POST',
							url : 'api/machine/atmLogInfo/dayBackupExcuter',
							params : {
								'date' : date
							}
						});
					},
					scope : this
				}]
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			}),
			tbar : ['->',{
				xtype : 'button',
				text : '查询',
				glyph : 0xf002,
				action : 'query'
			}]
		});
		this.callParent(arguments);
	}
	
});