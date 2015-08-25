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
			title : '磁盘信息',
			xtype : 'grid',
			store : 'machine.atmHardSoft.Disk',
			columns : [{
				header : '磁盘分区名称',
				dataIndex : 'name'
			}, {
				header : '磁盘文件系统',
				dataIndex : 'fileSys'
			}, {
				header : '磁盘总大小',
				renderer : function(value, metadata, record) {
					return (value / (1024 * 1024)).toFixed(1) + "GB";
				},
				dataIndex : 'totalSize'
			}, {
				header : '磁盘可用空间大小',
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