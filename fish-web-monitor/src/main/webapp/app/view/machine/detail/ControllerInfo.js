Ext.define('Eway.view.machine.detail.ControllerInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_ControllerInfo',

	requires : [],
	title : '设备控制信息',
	layout: {
        type: 'column',
    },
    closable:false,
    bodyPadding: 10,

  

	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'right',
				xtype : 'displayfield',
				labelWidth : 150,
				readOnly : true,
				width : '50%',
				bodyStyle:'margin-top:50px;'
			},
				items : [ {
							columnWidth : .24,
							name : 'remoteCommHist',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.commandRet+'</a>'
						}, {
							columnWidth : .24,
							name : 'remoteScreenAction',
							code : 'remoteScreen',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.screen+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name : 'netAction',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.net+'</a>'
						}, {
							columnWidth : .24,
							name : 'remoteBrowserAction',
							code : 'remoteBrowser',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.remoteBrowser+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name : 'logAction',
							code : 'takeLog',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.log+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name: 'closeAction',
							code : 'close',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.powerOff+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name : 'restartAction',
							code : 'restart',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.restart+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name : 'logicOpenAction',
							code : 'logicOpen',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.logicOpen+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name : 'logicCloseAction',
							code : 'logicClose',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.logicClose+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name : 'resetAction',
							code : 'reset',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.reset+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name : 'softwareListAction',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.softwareList+'</a>'
						}, {
							columnWidth : .24,
							name : 'processListAction',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.processList+'</a>'
						}, {
							columnWidth : .24,
							name : 'screenCameraAction',
							code : 'screenCamera',
							onlyText : true,
							value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.screenCamera+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name : 'remoteLookAction',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.remoteLook+'</a>'
						}, {
							columnWidth : .24,
							name : 'remoteCheckATMAction',
							code : 'remoteCheckATM',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.remoteCheckATM+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						} ]
				
		});

		this.callParent(arguments);
	}
    
});
