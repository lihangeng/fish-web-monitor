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
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId',
				flex : 1
			},{
				header : EwayLocale.atmLog.logDate,
				dataIndex : 'dateTime',
				flex : 1
			},{
				header : EwayLocale.atmLog.lastBackupTime,
				dataIndex : 'lastDoDate',
				flex : 1
			},{
				header : EwayLocale.atmLog.fileSize,
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
				header : EwayLocale.atmLog.backupResult,
				dataIndex : 'backupResult',
				width : 100,
				renderer : function(value,metaData,record,rowIndex,colIndex,store,view){
					if(value == 'SUCCESS'){
						return EwayLocale.tip.success;
					}
					else if(value == 'UNDO'){
						return EwayLocale.atmLog.noBegin;
					}
					else if(value == 'ERROR_CONNECT'){
						return EwayLocale.atmLog.connectFail;
					}
					else if(value == 'ERROR_NOLOG'){
						return EwayLocale.atmLog.noLog;
					}
					else if(value == 'ERROR'){
						return EwayLocale.atmLog.unKnownFail;
					}
				}
			},{
				xtype:'actioncolumn',
				flex : .5,
				dataIndex : 'backupResult',
				header: EwayLocale.agent.remote.screen.loading,
				items : [{
					icon : 'resources/images/down.gif',
					tooltip: EwayLocale.agent.remote.screen.loading,
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
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			}]
		});
		this.callParent(arguments);
	}
	
});