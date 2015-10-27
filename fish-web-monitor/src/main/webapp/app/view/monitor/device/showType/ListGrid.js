Ext.define('Eway.view.monitor.device.showType.ListGrid', {
	alias : 'widget.monitor_device_showtype_listgrid',
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
				xtype : 'rownumberer',
				resizable : true
			}, {
				header : Eway.locale.person.bankOrg.name,
				dataIndex : 'org'
			}, {
				header : Eway.locale.commen.terminalId,
				dataIndex : 'code',
				width : 100,
				tdCls : 'pointerLink'
			}, {
				header : Eway.locale.commen.ip,
				dataIndex : 'ip',
				width : 100
			}, {
				header : Eway.locale.monitor.devMonitor.comboxStatus.runStatus,
				dataIndex : 'runStatus',
				renderer : function(value, meta, record) {
				 	var runFatals = ['SubHealth', 'Maintain', 'Halt', 'ReBoot', 'StopAtmp', 'StopManmade', 'StopMod', 'StopUnCashIn', 'StopUnKnown'];
				 	var run = record.get('run'); 
				 	if (Ext.Array.contains(runFatals, run)) {
				 	 	return "<span class='fatalHighLight'>" + value + "</span>"; 
			 	 	} else { 
			 	 		return value; 
			 		} 
		 	 	},
				width : 100
			}, {
				header : Eway.locale.monitor.devMonitor.comboxStatus.modStatus,
				dataIndex : 'modStatus',
				renderer : function(value, meta, record) { 
					var mod = record.get('mod'); 
					if (mod == "Warning") { 
						return "<span class='warningHighLight'>" + value + "</span>"; 
					} else if (mod == "Fatal") { 
						return "<span class='fatalHighLight'>" + value + "</span>"; 
					} else { 
						return value; 
					} 
				},
				width : 100
			}, {
				header : Eway.locale.monitor.devMonitor.comboxStatus.boxStatus,
				dataIndex : 'boxStatus',
			  	renderer : function(value, meta, record) { 
			  		var boxFatals = ['Full', 'Low', 'Empty', 'High', 'Fatal']; 
			  		var box = record.get('box'); 
			  		if (Ext.Array.contains(boxFatals, box)) {
				  		return "<span class='fatalHighLight'>" + value + "</span>"; 
			  		} else { 
			  			return value; 
		  			} 
	  			},
				width : 100
			}, {
				header : Eway.locale.monitor.devMonitor.comboxStatus.netStatus,
				dataIndex : 'netStatus',
			  	renderer : function(value, meta, record) { 
			  		var net = record.get('net'); 
			  		if (net == "Warning") { 
			  			return "<span class='warningHighLight'>" + value + "</span>"; 
		  			} else if (net == "Fatal") { 
		  				return "<span class='fatalHighLight'>" + value + "</span>"; 
	  				} else { 
	  					return value; 
  					} 
				},
				width : 100
			}, {
				header : Eway.locale.monitor.devMonitor.retainCardCount,
				dataIndex : 'retainCardCount',
				width : 100
			}, {
				header : Eway.locale.monitor.devMonitor.cash.boxInitCount,
				dataIndex : 'boxInitCount',
				width : 100
			}, {
				header : Eway.locale.monitor.devMonitor.cash.boxCurrentCount,
				dataIndex : 'boxCurrentCount',
				width : 100
			}, {
				header : Eway.locale.commen.devTypeName,
				dataIndex : 'type',
				width : 100
			}, {
				header : Eway.locale.commen.orgNameBelongs,
				dataIndex : 'org',
				flex : 1
			}, {
				header : Eway.locale.commen.installAddr,
				dataIndex : 'address',
				flex : 1
			}, {
				header : Eway.locale.commen.seviceMode,
				dataIndex : 'seviceMode',
				width : 100
			}, {
				header : Eway.locale.commen.insideOutside,
				dataIndex : 'insideOutside',
				width : 100
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