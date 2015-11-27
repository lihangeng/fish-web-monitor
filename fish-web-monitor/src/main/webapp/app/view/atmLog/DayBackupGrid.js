Ext.define('Eway.view.atmLog.DayBackupGrid',{

	extend : 'Eway.view.base.Grid',
	alias : 'widget.atmLog_DayBackupGrid',
	
	
	initComponent : function(){
		var store = Ext.create('Eway.store.atmLog.DayBackup');
		store.loadPage(1);
		Ext.apply(this,{
			store : store,
			columns : [{
				header : EwayLocale.atmLog.backupDate,
				dataIndex : 'date',
				flex : 1
			},{
				header : EwayLocale.atmLog.dayBackupResult,
				dataIndex : 'result',
				flex : 1,
				renderer : function(value){
					if(value == 'SUCCESS'){
						return EwayLocale.atmLog.backupSuccess;
					}
					else if(value == "DOING"){
						return "<pre style='color:green'>"+EwayLocale.atmLog.backupProcess+"</pre>";
					}
					else if(value == "ERROR"){
						return "<pre style='color:red'>"+EwayLocale.atmLog.backupError+"</pre>";
					}
				}
			},{
				header : EwayLocale.version.task.actionTime,
				dataIndex : 'doTime',
				flex : 1
			},{
				header : EwayLocale.commen.endDataTime,
				dataIndex : 'endTime',
				flex : 1
			},{
				header : EwayLocale.atmLog.logDevSucAccount,
				dataIndex : 'deviceSucCount',
				flex : 1,
				align:'center',
				renderer:function(value,meta,record){
					if(value >0){
						return "<a class='link' href='#'>"+ value.toString() + "</a>";
					}else{
						return value;
					}
				}
			},{
				header : EwayLocale.atmLog.logDevFailAccount,
				dataIndex : 'deviceFailCount',
				align:'center',
				flex : 1,
				renderer:function(value,meta,record){
					if(value >0){
						return "<a class='link' href='#'>"+ value.toString() + "</a>";
					}else{
						return value;
					}
				}
			},{
				xtype:'actioncolumn',
				flex : .7,
				header: EwayLocale.atmLog.reform,
				dataIndex : 'backupResult',
				items : [{
					icon : 'resources/images/arrow_undo.png',
					tooltip: EwayLocale.atmLog.reform,
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
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			}]
		});
		this.callParent(arguments);
	}
	
});