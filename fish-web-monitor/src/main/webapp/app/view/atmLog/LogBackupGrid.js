Ext.define('Eway.view.atmLog.LogBackupGrid',{

	extend : 'Eway.view.base.Grid',
	alias : 'widget.atmLog_LogBackupGrid',
	
	
	initComponent : function(){
		var store = Ext.create('Eway.store.atmLog.LogBackup');
		Ext.apply(this,{
			store : store,
			columns : [{
				header : 'id',
				dataIndex : 'id',
				flex : 1,
				hidden : true
			},{
				header : Eway.locale.commen.terminalId,
				dataIndex : 'terminalId',
				flex : 1
			},{
				header : '日志日期',
				dataIndex : 'dateTime',
				flex : 1
			},{
				header : '最后一次备份时间',
				dataIndex : 'lastDoDate',
				flex : 1
			},{
				header : '文件大小',
				dataIndex : 'size',
				renderer: function(value,metadata,record){
                   	if(value>1024*1024*1024){
                   		return (value/(1024*1024*1024)).toFixed(2)+" GB";
                   	}else if(value>1024*1024){
                   		return (value/(1024*1024)).toFixed(1)+" MB";
                   	}else if(value>1024){
                   		return Math.ceil(value/1024)+" KB";
                   	}else{
                   		return value+" B";
                   	}
				},
				flex : 1
			},{
				header : '备份结果',
				dataIndex : 'backupResult',
				width : 100,
				renderer : function(value,metaData,record,rowIndex,colIndex,store,view){
					if(value == 'SUCCESS'){
						return '成功';
					}
					else if(value == 'UNDO'){
						return '未开始';
					}
					else if(value == 'ERROR_CONNECT'){
						return '连接失败';
					}
					else if(value == 'ERROR_NOLOG'){
						return '无日志';
					}
					else if(value == 'ERROR'){
						return '未知原因失败';
					}
				}
			},{
				xtype:'actioncolumn',
				flex : .5,
				dataIndex : 'backupResult',
				header: '下载',
				items : [{
					icon : 'resources/images/down.gif',
					tooltip: '下载',
					getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var result = record.get('backupResult'); 
						if(result == 'SUCCESS'){
							return 'changeCursor';
						}
						else if(result == 'UNDO'){
							return 'hiddenComp';
//							return 'changeCursor';
						}
						else if(result == 'ERROR_CONNECT'){
							return 'hiddenComp';
						}
						else if(result == 'ERROR_NOLOG'){
							return 'hiddenComp';
						}
						else if(result == 'ERROR'){
							return 'hiddenComp';
						}
					},
					handler : function(grid,rowIndex,colIndex){
						var record = grid.getStore().getAt(rowIndex);
						var url = 'api/machine/atmLog/downloadFile?deviceId='+record.get('terminalId')+"&dateTime="+record.get('dateTime'); 
						var iframe = document.getElementById('downloadFileFromWeb'); 
						iframe.src = url;
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