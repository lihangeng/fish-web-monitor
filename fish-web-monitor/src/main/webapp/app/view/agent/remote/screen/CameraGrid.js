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
			groupHeaderTpl: '{name}'+EwayLocale.agent.remote.screen.message
		});
		Ext.apply(this, {
			initRegion : true,
			tbar : [ {
				text : EwayLocale.agent.remote.screen.startcustom,
				action : 'startcustom',
				xtype : 'button'
			}, {
				text : EwayLocale.agent.remote.screen.stopcustom,
				action : 'stopcustom',
				xtype : 'button'
			}, {
				text : EwayLocale.agent.remote.screen.startadmin,
				action : 'startadmin',
				xtype : 'button'
			}, {
				text : EwayLocale.agent.remote.screen.stopadmin,
				action : 'stopadmin',
				xtype : 'button'
			}, {
				text : EwayLocale.agent.remote.screen.startadvertise,
				action : 'startadvertise',
				xtype : 'button'
			}, {
				text : EwayLocale.agent.remote.screen.stopadvertise,
				action : 'stopadvertise',
				xtype : 'button'
			} ],
			features: [groupingFeature],
			columns : [ {
				header : EwayLocale.commen.terminalId,
				sortable : true,
				width : 131,
				dataIndex : 'terminalId'
			},{
				header : EwayLocale.agent.remote.screen.startCameraDate,
				sortable : true,
				width : 131,
				dataIndex : 'startCameraDate'
			}, {
				header :EwayLocale.agent.remote.screen.stopCameraDate,
				sortable : true,
				width : 131,
				dataIndex : 'stopCameraDate'
			}, {
				header :EwayLocale.agent.remote.screen.monitorType,
				sortable : true,
				dataIndex : 'monitorType'
			}, {
				header : EwayLocale.agent.remote.screen.fileNameClient,
				sortable : true,
				width : 172,
				dataIndex : 'fileNameClient'
			}, {
				header : EwayLocale.commen.state,
				sortable : true,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return "<font color='red'>"+EwayLocale.agent.remote.screen.nowCamera+"</font>";
					}
					if (value == 4) {
						return EwayLocale.agent.remote.screen.finishCamera;
					}
					if (value == 0) {
						return "<font color='red'>"+EwayLocale.agent.remote.screen.videoLoad+"</font>";
					}
					if (value == 3) {
						return "<font color='red'>"+EwayLocale.agent.remote.screen.stopManage+"</font>";
					}
				}
			}, {
				header : EwayLocale.agent.remote.screen.manage,
				sortable : true,
				dataIndex : 'status',
				renderer : function(value, p, record) {
					if (value == 4) {
						return "<a class='link' href='javascript:void(0);'>"+EwayLocale.agent.remote.screen.loading+"</a>";
					}
				}
			} ]
		});
		this.callParent(arguments);
	}
});