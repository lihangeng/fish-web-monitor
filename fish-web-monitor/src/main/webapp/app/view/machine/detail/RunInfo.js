Ext.define('Eway.view.machine.detail.RunInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_runInfo',

	requires : ['Eway.view.machine.detail.run.TradingInfo',
	            'Eway.view.machine.detail.run.RetainCardInfo',
	            'Eway.view.machine.detail.run.CaseFaultInfo',
	            'Eway.view.machine.detail.run.OpenRateInfo',
	            'Eway.view.machine.detail.run.CashInitInfo'],
	height:800,
	closable:false,
	width:1024,
	title : '设备运行信息',
	layout: 'responsivecolumn',
    scrollable : 'y',
	isLoad : false,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype: 'tradingInfo',
		    	responsiveCls: 'big-50 small-100'
			},{
				xtype: 'faultTrend',
		    	responsiveCls: 'big-50 small-100'
			},{
				xtype: 'retainCardInfo',
		    	responsiveCls: 'big-50 small-100'
			},{
				xtype: 'openRateInfo',
		    	responsiveCls: 'big-50 small-100'
			},{
				xtype: 'cashInitInfo'
			}],
			listeners : {
				activate : function(panel) {
					if(!panel.isLoad){
//						this.refreshInfo(panel);
						panel.isLoad = true;
					}
				}
			}
		});

		this.callParent(arguments);
	},
	refreshInfo:function(panel){
		var terminalId = panel.up("tabpanel").getTerminalId();
		
		var store = panel.down("tradingInfo").myDataStore;
		var retainStore = panel.down("retainCardInfo").myDataStore;
		var faultStore = panel.down("faultTrend").myDataStore;
		var openRateStore = panel.down("openRateInfo").myDataStore;
		var cashInitStore = panel.down("cashInitInfo").myDataStore;
		
		faultStore.setBaseParam("terminalId",terminalId);
		openRateStore.setBaseParam("terminalId",terminalId);
		store.setBaseParam("terminalId",terminalId);
		retainStore.setBaseParam("terminalId",terminalId);
		cashInitStore.setBaseParam("terminalId",terminalId);
		store.load();
		retainStore.load();
		faultStore.load();
		openRateStore.load();
		cashInitStore.load();
	}
	
});
