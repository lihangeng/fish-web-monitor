Ext.define('Eway.view.machine.detail.ControllerInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_ControllerInfo',

	renderTo: document.body,
    tools: [ {
        itemId: 'refresh',
        type: 'refresh',
        tooltip: EwayLocale.button.refresh,
        callback: function() {
            // do refresh
        }
    },{
        itemId: 'collapse',
        type: 'collapse',
        hidden: true,
        tooltip: '收起',
        callback: function(panel) {
        	panel.down('#expand').show();
        	panel.down('#collapse').setHidden(true);
        	var displayfields = Ext.ComponentQuery.query("detail_ControllerInfo displayfield");
			Ext.Array.forEach(displayfields,function(displayfield,index,items){
				if(index>3){
				displayfield.setHidden(!displayfield.isHidden());
				}
			})
        }
    }, {
        type: 'expand',
        itemId: 'expand',
        tooltip: '展开',
        callback: function (panel) {
            panel.down('#collapse').show();
            panel.down('#expand').setHidden(true);
            var displayfields = Ext.ComponentQuery.query("detail_ControllerInfo displayfield");
			Ext.Array.forEach(displayfields,function(displayfield,index,items){
				if(displayfield.isHidden()){
					displayfield.setHidden(false);
					}
			})
        }
    }],

	requires : [],
	title : EwayLocale.deviceInfo.controllerInfo,
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
					name : 'remoteScreenAction',
					code : 'remoteScreen',
					onlyText : true,
					value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.screen+'</a>',
					listeners : {
						'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
					}
				},  {
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
				},{
						    columnWidth : .24,
						    hidden:true,
							name : 'remoteCommHist',
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.commandRet+'</a>'
						}, {
							columnWidth : .24,
							name : 'netAction',
						    hidden:true,
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.net+'</a>'
						}, {
							columnWidth : .24,
							name : 'remoteBrowserAction',
							code : 'remoteBrowser',
							 hidden:true,
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.remoteBrowser+'</a>',
//							listeners : {
//								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
//							}
						}, {
							columnWidth : .24,
							name : 'logAction',
							code : 'takeLog',
							hidden:true,
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.log+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name: 'closeAction',
							code : 'close',
							hidden:true,
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.powerOff+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name : 'restartAction',
							code : 'restart',
							hidden:true,
							onlyText : true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.restart+'</a>',
//							listeners : {
//								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
//							}
						}, {
							columnWidth : .24,
							name : 'softwareListAction',
							onlyText : true,
							hidden:true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.softwareList+'</a>'
						}, {
							columnWidth : .24,
							name : 'processListAction',
							onlyText : true,
							hidden:true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.processList+'</a>'
						}, {
							columnWidth : .24,
							name : 'screenCameraAction',
							code : 'screenCamera',
							onlyText : true,
							hidden:true,
							value : '<a href="#" class="link">'+EwayLocale.monitor.devMonitor.remote.screenCamera+'</a>',
							listeners : {
								'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
							}
						}, {
							columnWidth : .24,
							name : 'remoteLookAction',
							onlyText : true,
							hidden:true,
							value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.remoteLook+'</a>'
						}, {
							columnWidth : .24,
							name : 'remoteCheckATMAction',
							code : 'remoteCheckATM',
							hidden:true,
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
