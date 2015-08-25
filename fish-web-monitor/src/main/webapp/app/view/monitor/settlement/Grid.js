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
				text : '查询',
				glyph : 0xf002,
				action : 'query'
			}, {
				text : '详细信息',
				glyph : 0xf129,
				action : 'info'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ Ext.create('Ext.grid.RowNumberer'), {
				header : '设备号',
				dataIndex : 'termId'
			}, {
				header : '周期ID',
				dataIndex : 'uuId'
			}, {
				header : '尾箱余额',
				dataIndex : 'leftAmt',
				renderer: this.cnMoney
			}, {
				header : '结账日期',
				dataIndex : 'date'
			}, {
				header : '存款笔数',
				dataIndex : 'deposit'
			}, {
				header : '存款金额',
				dataIndex : 'depositAmt',
				renderer: this.cnMoney
			}, {
				header : '取款笔数',
				dataIndex : 'withdrawal'
			}, {
				header : '取款金额',
				dataIndex : 'withdrawalAmt',
				renderer: this.cnMoney
			}, {
				header : '交易总笔数',
				dataIndex : 'transaction'
			}, {
				header : '交易总金额',
				dataIndex : 'transactionAmt',
				renderer: this.cnMoney,
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true,
				displayMsg : '总共：{2}条，显示{0}-{1}'
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