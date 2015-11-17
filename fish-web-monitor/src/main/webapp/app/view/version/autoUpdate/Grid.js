
Ext.define('Eway.view.version.autoUpdate.Grid', {
	alias: 'widget.versionupdate_grid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],
	store: 'version.VersionAutoUpdate',
	border : false,

	initComponent: function() {
		var store = Ext.create('Eway.store.version.VersionAutoUpdate');
		Ext.apply(this, {
			store : store,
			tbar: ['->', {
				text: EwayLocale.button.search,//'查询',
				glyph : 0xf002,
				action: 'query'
			}],
			columns : [{
				header : EwayLocale.versionType.winTitle,//'软件分类',
				width : 130,
				dataIndex : 'versionType'
			},{
				header : EwayLocale.refs.terminalId,
				width : 100,
				dataIndex : 'terminalId'
			},{
				header : EwayLocale.refs.ip,//'设备IP',
				width : 120,
				dataIndex : 'deviceIp'
			},{

				header : EwayLocale.refs.orgName,
				width : 150,
				dataIndex : 'orgName'
			},{
				header:EwayLocale.version.task.versionNoBeforeUpdate,//'版本号',
				width : 180,
				dataIndex:'versionBeforeUpdate'
			},{
				header : EwayLocale.version.task.actionTime,//'更新时间',
				width : 150,
				dataIndex : 'excuteTime'
			},{
				header: EwayLocale.version.task.taskStatus,//'任务状态',
				width : 180,
				dataIndex :'taskStatus'
			},{
				header : EwayLocale.version.task.exceptVersion,//'预期版本号',
				flex : 1,
				dataIndex : 'version'
				
			}],
			
		bbar : Ext.create('Ext.PagingToolbar', {
			store : store,
			displayInfo : true,
			displayMsg : EwayLocale.commen.toolbar
		})
		});

		this.callParent(arguments);

	}
});