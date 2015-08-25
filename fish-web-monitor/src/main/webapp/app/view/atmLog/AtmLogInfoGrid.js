Ext.define('Eway.view.atmLog.AtmLogInfoGrid', {
	extend : 'Eway.view.base.Grid',
	alias : 'widget.atmLog_AtmLogInfoGrid',

	initComponent : function() {
		var store = Ext.create('Eway.store.atmLog.AtmLogInfo');
		store.loadPage(1);
		Ext.apply(this, {
			store : store,
			columns : [{
						header : '机构名称',
						dataIndex : 'orgName',
						flex : 1
					}, {
						header : '日志日期',
						dataIndex : 'backupDate',
						flex : 1
					}, {
						header : '备份成功台数',
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
						header : '备份失败台数',
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
						header : '总备份台数',
						dataIndex : 'totalBackupNumber',
						flex : 1
					}],
			bbar : Ext.create('Ext.PagingToolbar', {
						store : store,
						displayInfo : true
					}),
			tbar : ['->', {
						xtype : 'button',
						text : '查询',
						glyph : 0xf002,
						action : 'query'
					},{
						xtype : 'button',
						text : '导出',
						iconCls : 'exportBtn',
						action : 'export'
					}]

		});
		this.callParent(arguments);
	}

});