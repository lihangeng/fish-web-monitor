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
			groupHeaderTpl: '{name}'+Eway.locale.agent.remote.screen.message
		});
		Ext.apply(this, {
			initRegion : true,
			tbar : [ {
				text : Eway.locale.agent.remote.screen.startcustom,
				action : 'startcustom',
				xtype : 'button'
			}, {
				text : Eway.locale.agent.remote.screen.stopcustom,
				action : 'stopcustom',
				xtype : 'button'
			}, {
				text : Eway.locale.agent.remote.screen.startadmin,
				action : 'startadmin',
				xtype : 'button'
			}, {
				text : Eway.locale.agent.remote.screen.stopadmin,
				action : 'stopadmin',
				xtype : 'button'
			}, {
				text : Eway.locale.agent.remote.screen.startadvertise,
				action : 'startadvertise',
				xtype : 'button'
			}, {
				text : Eway.locale.agent.remote.screen.stopadvertise,
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
				header : Eway.locale.agent.remote.screen.startCameraDate,
				sortable : true,
				width : 131,
				dataIndex : 'startCameraDate'
			}, {
				header :Eway.locale.agent.remote.screen.stopCameraDate,
				sortable : true,
				width : 131,
				dataIndex : 'stopCameraDate'
			}, {
				header :Eway.locale.agent.remote.screen.monitorType,
				sortable : true,
				dataIndex : 'monitorType'
			}, {
				header : Eway.locale.agent.remote.screen.fileNameClient,
				sortable : true,
				width : 172,
				dataIndex : 'fileNameClient'
			}, {
				header : Eway.locale.commen.state,
				sortable : true,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return "<font color='red'>"+Eway.locale.agent.remote.screen.nowCamera+"</font>";
					}
					if (value == 4) {
						return Eway.locale.agent.remote.screen.finishCamera;
					}
					if (value == 0) {
						return "<font color='red'>"+Eway.locale.agent.remote.screen.videoLoad+"</font>";
					}
					if (value == 3) {
						return "<font color='red'>"+Eway.locale.agent.remote.screen.stopManage+"</font>";
					}
				}
			}, {
				header : Eway.locale.agent.remote.screen.manage,
				sortable : true,
				dataIndex : 'status',
				renderer : function(value, p, record) {
					if (value == 4) {
						return "<a class='link' href='javascript:void(0);'>"+Eway.locale.agent.remote.screen.loading+"</a>";
					}
				}
			} ]
		});
		this.callParent(arguments);
	}
});