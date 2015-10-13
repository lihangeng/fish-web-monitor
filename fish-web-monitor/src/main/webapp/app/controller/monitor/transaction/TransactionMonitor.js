Ext.define('Eway.controller.monitor.transaction.TransactionMonitor', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'monitor.transaction.Transaction','monitor.transaction.BlacklistDict','monitor.transaction.TransType','monitor.transaction.HostRet'],

	models : [ 'monitor.transaction.Transaction','Dict','monitor.transaction.TransType','monitor.transaction.HostRet'],

	views : [ 'monitor.transaction.TransactionMonitorView' ],


	refs : [ {
		ref : 'ewayView',
		selector : 'transactionMonitorView',
		autoCreate : true,
		xtype : 'transactionMonitorView'
	}, {
		ref : 'transactionGrid',
		selector : 'monitor_transaction_transactionGrid'
	} ],

	init : function() {

		var me = this;
	    var cometURL = location.protocol + "//" + location.host + Ext.cxtPath + "/cometd";
	    this.cometd = Ext.Cometd;
		this.cometd.configure(cometURL,'debug');

		this.listTran = new Array();

		this.interValArray = new Array();

		Ext.Ajax.request({
		    url: 'api/msg/transaction/queryTransType',
		    success: function(response){
		        var text = response.responseText;
		        me.getEwayView().down('transactionFilterForm').getForm().findField("transType").setValue(text);
		    }
		});

		Ext.Ajax.request({
		    url: 'api/msg/transaction/queryHostRet',
		    success: function(response){
		        var text = response.responseText;
		        me.getEwayView().down('transactionFilterForm').getForm().findField("hostRet").setValue(text);
		    }
		});

		this.control({
			'monitor_transaction_transactionGrid button[action=start]' : {
				click : this.startMonitor
			},
			'monitor_transaction_transactionGrid button[action=stop]' : {
				click : this.stopMonitor
			},
			'transactionMonitorView ' : {
				deactivate : this.deactivateJs
			}
		});


	},

	startMonitor : function() {

		var ewayView = this.getEwayView();
		var data = ewayView.down('transactionFilterForm').getForm().getValues();
		if(data.creditAccount == '' && data.debitAccount == '' && data.terminalId == ''){
			Eway.alert(Eway.locale.tip.business.transaction.transactionMonitor.input);
			return;
		}
		var grid = this.getTransactionGrid();

		grid.getStore().removeAll();
		grid.down('button[action="stop"]').setDisabled(false);
		grid.down('button[action="start"]').setDisabled(true);

		if(this.cometd.isDisconnected()) {
			this.cometd.handshake();
		}

		this.getEwayView().config.__transactionSub = this.cometd.subscribe("/service/transaction/join", this, this.onReceive);

		this.cometd.publish("/service/transaction/join",{
			userId : Eway.locale.tip.business.transaction.transactionMonitor.left,
			organizationId : ewayUser.getOrgId(),
			terminalId : data.terminalId,
			currency : data.currency,
			creditAccount : data.creditAccount,
			debitAccount : data.debitAccount,
			startAmt : data.startAmt,
			endAmt : data.endAmt,
			blacklist : data.blacklist
		});

		this.showListTran();
		this.removeListTran();
	},

	onReceive : function(message) {
		var data = Ext.decode(message.data);
		var ewayView = this.getEwayView();

		var form = ewayView.down('transactionFilterForm').getForm();
		var transType = Ext.decode(form.findField("transType").getValue());
		var hostRet = Ext.decode(form.findField("hostRet").getValue());

		Ext.Array.each(hostRet.data,function(iter,index,dataSelf){
			if(data.hostRet==iter.hostRet){
				data.hostRet = iter.hostRet;
			}
		});
		Ext.Array.each(transType.data,function(iter,index,dataSelf){
			if(data.transCode==iter.transCode){
				data.transCode = iter.codeDesc;
			}
		});

		this.listTran.push(data);
	},

	stopMonitor : function() {
		this.getEwayView().closeMonitor();
		this.getTransactionGrid().down('button[action="start"]').setDisabled(false);
		this.getTransactionGrid().down('button[action="stop"]').setDisabled(true);
//		this.getTransactionGrid().getStore().removeAll();


		this.deactivateJs();
		this.listTran.splice(0, this.listTran.length);
	},


	deactivateJs : function() {

		var ar = this.interValArray.splice(0, this.interValArray.length);

		Ext.Array.each(ar, function(iter, index, dataSelf) {
			clearInterval(iter);
		});

		clearInterval(this.removeIntegerval);
	},

	removeListTran : function() {
		var win = this.getEwayView();
		var store = this.getTransactionGrid().getStore();
		this.removeIntegerval = setInterval(function() {
			while(store.getCount() > 20) {
				store.remove(store.getAt(20));
			}
		}, 100);

	},

	showListTran : function() {
		var me = this;
		var win = this.getEwayView();
		var store = this.getTransactionGrid().getStore();

		for ( var int = 0; int < 4; int++) {
			this.interValArray.push(setInterval(function() {

				var ar = me.listTran.splice(0, 2);

				Ext.Array.each(ar, function(iter, index, dataSelf) {
					var record = Ext.create('Eway.model.monitor.transaction.Transaction',iter);
					store.insert(0, record);
				});

			}, (int + 1) * 30));
		}
	}
});