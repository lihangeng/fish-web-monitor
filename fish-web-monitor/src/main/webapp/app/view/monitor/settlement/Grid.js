Ext.define('Eway.view.monitor.settlement.Grid', {
	alias : 'widget.monitor_settlement_grid',
	extend : 'Eway.view.base.Grid',
	requires : [ 'Eway.lib.Util' ],
	border : false,
	autoFit : true,
	initComponent : function() {
		var store = Ext.create('Eway.store.monitor.settlement.Settlement');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : Eway.locale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : Eway.locale.commen.info,
				glyph : 0xf129,
				action : 'info'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ Ext.create('Ext.grid.RowNumberer'), {
				header : Eway.locale.commen.terminalId,
				dataIndex : 'termId'
			}, {
				header : Eway.locale.monitor.business.settlement.uuId,
				dataIndex : 'uuId'
			}, {
				header : Eway.locale.monitor.business.settlement.endAmt,
				dataIndex : 'leftAmt',
				renderer: this.cnMoney
			}, {
				header : Eway.locale.monitor.business.settlement.leftDate,
				dataIndex : 'date'
			}, {
				header : Eway.locale.monitor.business.settlement.cimNum,
				dataIndex : 'deposit'
			}, {
				header : Eway.locale.monitor.business.settlement.cimAmt,
				dataIndex : 'depositAmt',
				renderer: this.cnMoney
			}, {
				header : Eway.locale.monitor.business.settlement.cdmNum,
				dataIndex : 'withdrawal'
			}, {
				header : Eway.locale.monitor.business.settlement.cdmAmt,
				dataIndex : 'withdrawalAmt',
				renderer: this.cnMoney
			}, {
				header : Eway.locale.monitor.business.settlement.totalNum,
				dataIndex : 'transaction'
			}, {
				header : Eway.locale.monitor.business.settlement.tranAmt,
				dataIndex : 'transactionAmt',
				renderer: this.cnMoney,
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true,
				displayMsg : Eway.locale.commen.toolbar
			})
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	},
	cnMoney : function(v) {
		v = (Math.round((v-0)*100))/100;  
	    v = (v == Math.floor(v)) ? v + ".00" : ((v*10 == Math.floor(v*10)) ? v + "0" : v);  
	    v = String(v);  
	    var ps = v.split('.');  
	    var whole = ps[0];  
	    var sub = ps[1] ? '.'+ ps[1] : '.00';  
	    var r = /(\d+)(\d{3})/;  
	    while (r.test(whole)) {  
	        whole = whole.replace(r, '$1' + ',' + '$2');  
	    }  
	    v = whole + sub;  
	    if(v.charAt(0) == '-'){  
	        return '-￥' + v.substr(1);  
	    }  
	    return "￥" +  v;
	}
});