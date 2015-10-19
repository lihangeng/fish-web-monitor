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
				header : Eway.locale.commen.terminalId,
				dataIndex : 'terminalId',
				flex : 1
			},{
				header : Eway.locale.atmLog.logDate,
				dataIndex : 'dateTime',
				flex : 1
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
				iconCls : 'exportToExcel',
				glyph : 0xf1c3,
				action : 'export'
			}]
		});
		this.callParent(arguments);
	}
	
});