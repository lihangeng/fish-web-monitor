
Ext.define('Eway.view.monitor.transaction.TransactionMonitorView', {
	extend: 'Eway.view.base.Panel',
	alias: 'widget.transactionMonitorView',

	requires: ['Eway.view.monitor.transaction.TransactionGrid',
				'Eway.view.monitor.transaction.TransactionFilterForm'],

	title: Eway.locale.monitor.business.transaction.transactionMonitor.title,
	layout: 'border',

	config : {
		_transactionSub : undefined,
		_transactionHandsubSub : undefined,
		_flag : false
	},

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'transactionFilterForm'
			}, {
				region: 'center',
				/*title: '交易情况',*/
				xtype: 'monitor_transaction_transactionGrid'
			}],
			listeners : {
				scope : this,
				activate : function(panel){
//					this.doCometd();
//					this._flag = true;
//					panel.down('button[action="stop"]').setDisabled(false);
//					panel.down('button[action="start"]').setDisabled(true);
					panel.down('button[action="start"]').setDisabled(false);
					panel.down('button[action="stop"]').setDisabled(true);
				},
				deactivate : this.closeMonitor
			}
		});
		this.callParent(arguments);
	},
	/*
	doCometd : function(){
		var cometd = Ext.Cometd;
		var cometURL = location.protocol + "//" + location.host + Ext.cxtPath +"/cometd";
		cometd.configure(cometURL);
		if(this.config._transactionHandsubSub){
			cometd.removeListener(this.config._transactionHandsubSub);
		}
		this.config._transactionHandsubSub = cometd.addListener('/meta/handshake',Ext.bind(this._metaHandshake,this));
		cometd.handshake();
	},
	_metaHandshake : function(message){
		var data = this.down('transactionFilterForm').getForm().getValues();
		if(message.successful){
			var isDis = Ext.Cometd.isDisconnected();
			if(!isDis){
				this._connectionInitalized();
				var params = {
					userId : test_userId+Math.random()*10,
					organizationId : ewayUser.getOrgId(),
					terminalId:data.terminalId,
					currency:data.currency,
					creditAccount:data.creditAccount,
					debitAccount:data.debitAccount,
					startAmt:data.startAmt,
					endAmt:data.endAmt,
					blacklist:data.blacklist
				};
				Ext.defer(this.publish,2000,this,[params]);
			}
			else {
				Ext.defer(this.doCometd,1000,this);
			}
		}
	},
	publish : function(params){
		if(this._flag){
			Ext.Cometd.publish('/service/transaction/join',params);
		}

	},
	_connectionInitalized : function(){
		if(this.config._transactionSub){
			Ext.Cometd.removeListener(this.config._transactionSub);
		}
		this.config._transactionSub = Ext.Cometd.addListener('/service/transaction/join',Ext.bind(this.onReceive,this));
	},
	onReceive:function(message){
		var grid = this.down('monitor_transaction_transactionGrid');
		var data = Ext.decode(message.data);
		var record = Ext.ModelManager.create(data, 'Eway.model.monitor.transaction.Transaction');
		grid.getStore().insert(0,record);
		if(grid.getStore().getCount()>50){
			grid.getStore().remove(grid.getStore().getAt(50));
		}
	},*/
	closeMonitor : function(){
		if(this.config._transactionHandsubSub){
			Ext.Cometd.removeListener(this.config._transactionHandsubSub);
			this.config._transactionHandsubSub = null;
		}
		if(this.config._transactionSub){
			Ext.Cometd.removeListener(this.config._transactionSub);
			this.config._transactionSub = null;
		}
		Ext.Cometd.disconnect();
		this._flag=false;
	}
});