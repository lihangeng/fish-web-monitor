Ext.define('Eway.view.monitor.device.showType.BoxGrid', {
	alias : 'widget.monitor_device_showtype_boxgrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	autoFit : true,
	viewConfig : {
		forceFit : true,
		stripeRows : true
	},
	initComponent : function() {
		var store = Ext.create('Eway.store.monitor.device.DataView', {});
		// store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,

			columns : [ {
				xtype : 'rownumberer'
			}, {
				header : Eway.locale.person.bankOrg.name,
				dataIndex : 'org',
				flex : 1
			}, {
				header : Eway.locale.commen.terminalId,
				dataIndex : 'code',
				flex : 1,
				tdCls : 'pointerLink'
			}, {
				header : Eway.locale.monitor.devMonitor.comboxStatus.boxStatus,
				dataIndex : 'boxStatus',
				renderer : function(value, meta, record) {
			    	var boxFatals = [ 'Full', 'Empty', 'High', 'Fatal' ]; 
			    	var box = record.get('box');
				 	if(Ext.Array.contains(boxFatals,box)) { 
				 		return "<span class='fatalHighLight'>"+ value + "</span>"; 
			 		} else if (box == 'Low') {
		 				return "<span class='warningHighLight'>"+ value + "</span>"; 
	 				} else { 
 						return value; 
					} 
				},
				flex : 1,
				tdCls : 'pointerLink'
			}, {
				header : Eway.locale.monitor.devMonitor.cash.boxInitCount,
				dataIndex : 'boxInitCount',
				flex : 1
			}, {
				header : Eway.locale.monitor.devMonitor.cash.boxCurrentCount,
				dataIndex : 'boxCurrentCount',
				flex : 1
			}, {
				header : Eway.locale.monitor.devMonitor.cash.cashboxLimit,
				dataIndex : 'cashboxLimit',
				flex : 1
			}, {
				header : Eway.locale.monitor.devMonitor.retainCardCount,
				dataIndex : 'retainCardCount',
			 	renderer : function(value, meta, record) { 
			 		if(value >= 20) { 
			 			return "<span class='fatalHighLight'>"+ value + "</span>"; 
		 			} else { 
		 				return value; 
	 				} 
 				},
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true,
				displayMsg : Eway.locale.tip.displayMessage
			})
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});