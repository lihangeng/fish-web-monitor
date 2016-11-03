Ext.define('Eway.view.machine.detail.basic.OftenControllerInfo', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.detail_basic_OftenControllerInfo',
	

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
				labelWidth : 105,
				width : '25%'
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
			}]
		});
		
		this.callParent(arguments);
	}
});
