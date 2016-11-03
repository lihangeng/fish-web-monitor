Ext.define('Eway.view.machine.detail.basic.controllerInfo', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.detail_basic_controllerInfo',
	

	requires : [],
	layout: {
        type: 'column',
    },
    closable:false,
    //bodyPadding: 10,
    

	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			defaults : {
				xtype : 'displayfield',
				labelWidth : 115,
				width : '25%'
			},
			items : [{
			    columnWidth : .25,
				name : 'remoteCommHist',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.commandRet+'</a>'
			}, {
				columnWidth : .25,
				name : 'netAction',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.net+'</a>'
			}, {
				columnWidth : .25,
				name : 'remoteBrowserAction',
				code : 'remoteBrowser',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.remoteBrowser+'</a>',
				listeners : {
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				columnWidth : .25,
				name : 'logAction',
				code : 'takeLog',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.log+'</a>',
				listeners : {
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				columnWidth : .25,
				name: 'closeAction',
				code : 'close',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.powerOff+'</a>',
				listeners : {
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				columnWidth : .25,
				name : 'restartAction',
				code : 'restart',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.restart+'</a>',
				listeners : {
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				columnWidth : .25,
				name : 'softwareListAction',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.softwareList+'</a>'
			}, {
				columnWidth : .25,
				name : 'processListAction',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.processList+'</a>'
			}, {
				columnWidth : .25,
				name : 'screenCameraAction',
				code : 'screenCamera',
				onlyText : true,
				value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.screenCamera+'</a>',
				listeners : {
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				columnWidth : .25,
				name : 'remoteLookAction',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.remoteLook+'</a>'
			}, {
				columnWidth : .25,
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
