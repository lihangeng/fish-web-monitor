Ext.define('Eway.view.machine.detail.basic.AllControllerInfo', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.allControllerInfo',
	

	requires : [],
	layout: {
        type: 'column'
    },
    closable:false,

	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			defaults : {
				xtype : 'displayfield',
				labelWidth : 115,
				width : '25%'
			},
			items : [ {
				columnWidth : .25,
				name : 'remoteScreenAction',
				code : 'remoteScreen',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.screen+'</a>',
				listeners : {
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},  {
				columnWidth : .25,
				name : 'logicOpenAction',
				code : 'logicOpen',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.logicOpen+'</a>',
				listeners : {
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				columnWidth : .25,
				name : 'logicCloseAction',
				code : 'logicClose',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.logicClose+'</a>',
				listeners : {
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				columnWidth : .25,
				name : 'resetAction',
				code : 'reset',
				onlyText : true,
				value : '<a href="#" class="link" >'+EwayLocale.monitor.devMonitor.remote.reset+'</a>',
				listeners : {
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
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
			} ],
			listeners:{
				afterrender:function(_this){
					var fields = _this.up("detail_basicInfo").down("allControllerInfo").query("displayfield[hidden=false]") ;
					Ext.Array.forEach(fields,function(item,index,items){
						if(index<4){
							_this.up("detail_basicInfo").down("detail_basic_OftenControllerInfo").add(item);
						}
						else{
							_this.up("detail_basicInfo").down("detail_basic_hiddenControllerInfo").add(item);
						}
					});
				}
			}
		});
		
		this.callParent(arguments);
	}
});
