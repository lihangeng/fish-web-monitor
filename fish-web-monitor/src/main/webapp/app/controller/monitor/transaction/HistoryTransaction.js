Ext.define('Eway.controller.monitor.transaction.HistoryTransaction', {
	extend : 'Eway.controller.base.FishController',

	stores : ['monitor.transaction.HistoryTransaction','monitor.transaction.BlacklistDict','monitor.transaction.TransType','monitor.transaction.HostRet'],

	models : ['monitor.transaction.Transaction','Dict','monitor.transaction.TransType','monitor.transaction.HostRet'],

	views : ['Eway.view.monitor.transaction.HistoryTransactionMonitorView'],

	refs : [ {
		ref : 'ewayView',
		selector : 'monitor_transaction_transview',
		autoCreate : true,
		xtype : 'monitor_transaction_transview'
	}, {
		ref : 'historyForm',
		selector : 'monitor_transaction_transForm'
	}, {
		ref : 'Grid',
		selector : 'monitor_transaction_transGrid'
	} ],

	init : function() {
		this.control({
			'monitor_transaction_transGrid button[action=query]' : {
				click : this.onQueryHistory
			}
		});
		//this.onQueryHistory();
	},

	onQueryHistory : function() {
		var form = this.getHistoryForm().getForm();
		var bool = form.isValid();
		var store = 	this.getGrid().getStore();
		if (bool == true) {
			var data = form.getValues();
			if(data.terminalId!=''){
				store.setUrlParamsByObject(data);
				store.setBaseParam('organizationId', ewayUser.getOrgId());
				store.load({
				    	scope   : this,
					    callback: function(records, operation, success) {
					        if(success==true){
								if(records&&records.length > 0&&records[0].data.transId==0){
									Eway.alert(records[0].data.termId);
									store.removeAll();
						    	}
							}
						}
				    });
			}else{
				Eway.alert(EwayLocale.tip.business.transaction.historyTransaction.input);
			}
		}

	}

});