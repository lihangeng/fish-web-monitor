Ext.define('Eway.view.parameter.paramMonitor.TaskGrid', {
	alias : 'widget.parameter_paramMonitor_TaskGrid',
	extend : 'Eway.view.base.Grid',

	config:{
		jobId:0
	},
	initComponent : function() {
		var store = Ext.create('Eway.store.parameter.paramMonitor.TaskMonitor');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query',
				code : 'paramMonitorTaskQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			 }],
			columns : [ {
				header : EwayLocale.param.paramDownloadMonitor.taskId,
				dataIndex : 'id',
				width:150
			},{
				header : EwayLocale.machine.atmGroup.terminalId,
				dataIndex : 'terminalId',
				width:150
			},{
				header : EwayLocale.param.paramDownloadMonitor.versionNo,
				dataIndex : 'versionNo',
				width:150
			},{
				header : EwayLocale.param.paramDownloadMonitor.downloadStartTime,
				dataIndex : 'downloadStartTime',
				width:150
			},{
				header : EwayLocale.param.paramDownloadMonitor.downloadFinishTime,
				dataIndex : 'downloadFinishTime',
				width:150
			}/*,{
				header : '是否成功',
				dataIndex : 'success',
				width:150
			}*/,{
				header : EwayLocale.param.paramDownloadMonitor.reason,
				dataIndex : 'reason',
				width:150
			},{
				header :EwayLocale.param.paramDownloadMonitor.taskStatus,
				dataIndex : 'taskStatus',
				flex:1,
				renderer: function(value,meta,record) {
					return '<font style="color:red;cursor:pointer">'+value+'</font>';
				},
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