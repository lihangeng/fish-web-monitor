Ext.define('Eway.view.atmLog.successAtmLogInfo.SuccessAtmLogInfoGrid',{

	extend : 'Eway.view.base.Grid',
	alias : 'widget.atmLog_successAtmLogInfo_SuccessAtmLogInfoGrid',
	
	
	initComponent : function(){
		var store = Ext.create('Eway.store.atmLog.SuccessAtmLogInfo');
//		store.loadPage(1);
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
				header : Eway.locale.atmLog.logDate,
				dataIndex : 'dateTime',
				flex : 1
			},{
				header : Eway.locale.atmLog.lastDoDate,
				dataIndex : 'lastDoDate',
				width : 160
			},{
				xtype:'actioncolumn',
				flex : .5,
				dataIndex : 'backupResult',
				header: Eway.locale.agent.remote.screen.loading,
				items : [{
					icon : 'resources/images/down.gif',
					tooltip: Eway.locale.agent.remote.screen.loading,
					getClass :'changeCursor',
					handler : function(grid,rowIndex,colIndex){
						var record = grid.getStore().getAt(rowIndex);
						var url = 'api/machine/atmLog/downloadFile?deviceId='+record.get('terminalId')+"&dateTime="+record.get('dateTime'); 
						var iframe = document.getElementById('downloadFileFromWeb'); 
						iframe.src = url;
					},
					scope : this
				}]
			},{
				header : Eway.locale.atmLog.backupResult,
				dataIndex : 'backupResult',
				width : 100,
				renderer : function(value,metaData,record,rowIndex,colIndex,store,view){
					if(value == 'SUCCESS'){
						return Eway.locale.tip.success;
					}else{
						return Eway.locale.tip.fail;
					}
				}
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			}),
			tbar : ['->',{
				xtype : 'button',
				text : Eway.locale.button.exported,
				glyph : 0xf1c3,
				action : 'export'
			}]
		});
		this.callParent(arguments);
	}
	
});