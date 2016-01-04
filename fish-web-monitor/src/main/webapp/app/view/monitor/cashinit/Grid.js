Ext.define('Eway.view.monitor.cashinit.Grid', {
	alias : 'widget.monitor_cashinit_grid',
	extend : 'Eway.view.base.Grid',
	requires : [ 'Eway.lib.Util' ],
	border : false,
	autoFit : true,
	initComponent : function() {
		var store = Ext.create('Eway.store.monitor.cashinit.CashInit');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : EwayLocale.commen.info,
				glyph : 0xf129,
				action : 'info',
				code : 'cashInitInfo',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ Ext.create('Ext.grid.RowNumberer'), {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'termId'
			}, {
				header : EwayLocale.monitor.business.cashInit.uuId,
				dataIndex : 'uuId'
			}, {
				header : EwayLocale.monitor.devMonitor.cash.initAmount,
				dataIndex : 'amt',
				renderer: this.cnMoney
			}, {
				header : EwayLocale.monitor.business.cashInit.date,
				dataIndex : 'date',
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true,
				displayMsg : EwayLocale.commen.toolbar
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
	    return   v;
	}
});