Ext.define('Eway.view.agent.remote.DiskDetail', {

	extend : 'Ext.window.Window',
	
	alias : 'widget.remote_DiskDetail',
	
	width : 550,
	height : 350,
//	maximizable : true,
	modal: true,
	title:'ATM体检',
	resizable: false,
	constrainHeader: true,
	maximizable: true,
	layout : 'fit',
	initComponent : function() {
		var me = this;
		this.items = [{
			title : Eway.locale.agent.remote.discInfo,
			xtype : 'grid',
			store : 'machine.atmHardSoft.Disk',
			columns : [{
				header : Eway.locale.agent.remote.discName,
				dataIndex : 'name'
			}, {
				header : Eway.locale.agent.remote.fileSys,
				dataIndex : 'fileSys'
			}, {
				header : Eway.locale.agent.remote.totalSize,
				renderer : function(value, metadata, record) {
					return (value / (1024 * 1024)).toFixed(1) + "GB";
				},
				dataIndex : 'totalSize'
			}, {
				header : Eway.locale.agent.remote.freeSize,
				renderer : function(value, metadata, record) {
					return (value / (1024 * 1024)).toFixed(1) + "GB";
				},
				dataIndex : 'freeSize',
				flex : 1
			} ]
		} ];
		this.callParent(arguments);
	}
	
});