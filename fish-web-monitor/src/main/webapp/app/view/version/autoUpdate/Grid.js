
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
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			}],
			columns : [{
				header : '软件分类',
				width : 130,
				dataIndex : 'versionType'
			},{
				header : Eway.locale.commen.terminalId,
				width : 100,
				dataIndex : 'terminalId'
			},{
				header : '设备IP',
				width : 120,
				dataIndex : 'deviceIp'
			},{

				header : Eway.locale.commen.orgNameBelongs,
				width : 150,
				dataIndex : 'orgName'
			},{
				header:'版本号',
				width : 180,
				dataIndex:'versionBeforeUpdate'
			},{
				header : '更新时间',
				width : 150,
				dataIndex : 'excuteTime'
			},{
				header: '任务状态',
				width : 180,
				dataIndex :'taskStatus'
			},{
				header : '预期版本号',
				flex : 1,
				dataIndex : 'version'
				
			}],
			
		bbar : Ext.create('Ext.PagingToolbar', {
			store : store,
			displayInfo : true,
			displayMsg : Eway.locale.commen.toolbar
		})
		});

		this.callParent(arguments);

	}
});