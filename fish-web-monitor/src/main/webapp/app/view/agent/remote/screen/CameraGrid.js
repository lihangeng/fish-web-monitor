Ext.define('Eway.view.agent.remote.screen.CameraGrid', {
	extend : 'Eway.view.base.Grid',
	alias : 'widget.agent_remote_screen_cameraGrid',

	store : 'agent.ScreenCamera',

	border : false,
	autoFit : true,
	columnLines : true,
	viewConfig : {
		// 为了解决me.loadMask.bindStore(store)错误的问题
		loadMask : false
	},

	initComponent : function() {
		var groupingFeature = Ext.create('Ext.grid.feature.Grouping', {
			groupHeaderTpl: '{name}信息'
		});
		Ext.apply(this, {
			initRegion : true,
			tbar : [ {
				text : '开始录制客户前屏',
				action : 'startcustom',
				xtype : 'button'
			}, {
				text : '停止录制客户前屏',
				action : 'stopcustom',
				xtype : 'button'
			}, {
				text : '开始录制管理后屏',
				action : 'startadmin',
				xtype : 'button'
			}, {
				text : '停止录制管理后屏',
				action : 'stopadmin',
				xtype : 'button'
			}, {
				text : '开始录制广告屏',
				action : 'startadvertise',
				xtype : 'button'
			}, {
				text : '停止录制广告屏',
				action : 'stopadvertise',
				xtype : 'button'
			} ],
			features: [groupingFeature],
			columns : [ {
				header : Eway.locale.commen.terminalId,
				sortable : true,
				width : 131,
				dataIndex : 'terminalId'
			},{
				header : '开始录制时间',
				sortable : true,
				width : 131,
				dataIndex : 'startCameraDate'
			}, {
				header : '结束录制时间',
				sortable : true,
				width : 131,
				dataIndex : 'stopCameraDate'
			}, {
				header : '屏幕类型',
				sortable : true,
				dataIndex : 'monitorType'
			}, {
				header : '文件名称',
				sortable : true,
				width : 172,
				dataIndex : 'fileNameClient'
			}, {
				header : Eway.locale.commen.state,
				sortable : true,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return "<font color='red'>正在录制...</font>";
					}
					if (value == 4) {
						return "完成录制";
					}
					if (value == 0) {
						return "<font color='red'>正在将视频文件下载至服务端...</font>";
					}
					if (value == 3) {
						return "<font color='red'>自动停止.如需取得视频文件,请联系管理员!</font>";
					}
				}
			}, {
				header : '操作',
				sortable : true,
				dataIndex : 'status',
				renderer : function(value, p, record) {
					if (value == 4) {
						return "<a class='link' href='javascript:void(0);'>下载</a>";
					}
				}
			} ]
		});
		this.callParent(arguments);
	}
});