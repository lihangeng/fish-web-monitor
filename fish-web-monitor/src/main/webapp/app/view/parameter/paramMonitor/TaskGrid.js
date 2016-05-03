Ext.define('Eway.view.parameter.paramMonitor.TaskGrid', {
	alias : 'widget.parameter_paramMonitor_TaskGrid',
	extend : 'Eway.view.base.Grid',

	
	initComponent : function() {
		var store = Ext.create('Eway.store.parameter.paramMonitor.TaskMonitor');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			 }],
			columns : [ {
				header : '任务ID',
				dataIndex : 'id',
				width:150
			},{
				header : '设备终端号',
				dataIndex : 'terminalId',
				width:150
			},{
				header : '版本号',
				dataIndex : 'versionNo',
				width:150
			},{
				header : '下载开始时间',
				dataIndex : 'downloadStartTime',
				width:150
			},{
				header : '下载开始时间',
				dataIndex : 'downloadFinishTime',
				width:150
			}/*,{
				header : '是否成功',
				dataIndex : 'success',
				width:150
			}*/,{
				header : '原因',
				dataIndex : 'reason',
				width:150
			},{
				header : '任务状态',
				dataIndex : 'taskStatus',
				flex:1
			}],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});