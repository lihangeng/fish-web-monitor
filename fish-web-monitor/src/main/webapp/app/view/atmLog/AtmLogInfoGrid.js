Ext.define('Eway.view.atmLog.AtmLogInfoGrid', {
	extend : 'Eway.view.base.Grid',
	alias : 'widget.atmLog_AtmLogInfoGrid',

	initComponent : function() {
		var store = Ext.create('Eway.store.atmLog.AtmLogInfo');
		store.loadPage(1);
		Ext.apply(this, {
			store : store,
			columns : [{
						header : Eway.locale.person.bankOrg.name,
						dataIndex : 'orgName',
						flex : 1
					}, {
						header : Eway.locale.atmLog.logDate,
						dataIndex : 'backupDate',
						flex : 1
					}, {
						header : Eway.locale.atmLog.backupSucAmount,
						dataIndex : 'backupSuccessNumber',
						flex : 1,
						renderer:function(value,meta,record){
							if(value >0){
								return "<a class='link' href='#'>"+ value.toString() + "</a>";
							}else{
								return value;
							}
						}
					}, {
						header : Eway.locale.atmLog.backupFailAmount,
						dataIndex : 'backupErrorNumber',
						flex : 1,
						renderer:function(value,meta,record){
							if(value >0){
								return "<a class='link' href='#'>"+ value.toString() + "</a>";
							}else{
								return value;
							}
						}
					}, {
						header : Eway.locale.atmLog.backupAllAmount,
						dataIndex : 'totalBackupNumber',
						flex : 1
					}],
			bbar : Ext.create('Ext.PagingToolbar', {
						store : store,
						displayInfo : true
					}),
			tbar : ['->', {
						xtype : 'button',
						text : Eway.locale.button.search,
						glyph : 0xf002,
						action : 'query'
					},{
						xtype : 'button',
						text : Eway.locale.button.exported,
						glyph : 0xf1c3,
						action : 'export'
					}]

		});
		this.callParent(arguments);
	}

});