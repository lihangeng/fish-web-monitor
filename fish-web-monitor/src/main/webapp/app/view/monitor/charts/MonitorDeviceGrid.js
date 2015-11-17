Ext.define('Eway.view.monitor.charts.MonitorDeviceGrid',{
	extend : 'Ext.grid.Panel',
	alias : 'widget.monitor_device_grid',

	scrollable:'both',
	initComponent : function() {
		var store = Ext.create('Eway.store.monitor.charts.DonutChartsDetail', {});
		Ext.apply(this, {
			store : store,
			columns : [  {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'code',
				width:80
			},  {
				header : EwayLocale.monitor.devMonitor.comboxStatus.runStatus,
				dataIndex : 'runStatus',
				width:80,
				renderer : function(value, meta, record) {
				 	var runFatals = ['SubHealth', 'Maintain', 'Halt', 'ReBoot', 'StopAtmp', 'StopManmade', 'StopMod', 'StopUnCashIn', 'StopUnKnown'];
				 	var run = record.get('run'); 
				 	if (Ext.Array.contains(runFatals, run)) {
				 	 	return "<span class='fatalHighLight'>" + value + "</span>"; 
			 	 	} else { 
			 	 		return value; 
			 		} 
		 	 	}
			}, {
				header : EwayLocale.monitor.devMonitor.comboxStatus.modStatus,
				dataIndex : 'modStatus',
				width:80,
//				locked:true,
				renderer : function(value, meta, record) { 
					var mod = record.get('mod'); 
					if (mod == "Warning") { 
						return "<span class='warningHighLight'>" + value + "</span>"; 
					} else if (mod == "Fatal") { 
						return "<span class='fatalHighLight'>" + value + "</span>"; 
					} else { 
						return value; 
					} 
				}
			}, {
				header : EwayLocale.monitor.devMonitor.comboxStatus.boxStatus,
				dataIndex : 'boxStatus',
				width:80,
//				locked:true,
			  	renderer : function(value, meta, record) { 
			  		var boxFatals = ['Full', 'Low', 'Empty', 'High', 'Fatal']; 
			  		var box = record.get('box'); 
			  		if (Ext.Array.contains(boxFatals, box)) {
				  		return "<span class='fatalHighLight'>" + value + "</span>"; 
			  		} else { 
			  			return value; 
		  			} 
	  			}
			}, {
				header : EwayLocale.monitor.devMonitor.comboxStatus.netStatus,
				dataIndex : 'netStatus',
				width:80,
//				locked:true,
			  	renderer : function(value, meta, record) { 
			  		var net = record.get('net'); 
			  		if (net == "Warning") { 
			  			return "<span class='warningHighLight'>" + value + "</span>"; 
		  			} else if (net == "Fatal") { 
		  				return "<span class='fatalHighLight'>" + value + "</span>"; 
	  				} else { 
	  					return value; 
  					} 
				}
			},{
				header : EwayLocale.commen.ip,
				dataIndex : 'ip'
			}, {
				header : EwayLocale.person.bankOrg.name,
				dataIndex : 'org'
			}, {
				header : EwayLocale.monitor.devMonitor.retainCardCount,
				dataIndex : 'retainCardCount'
			}, {
				header : EwayLocale.monitor.devMonitor.cash.boxInitCount,
				dataIndex : 'boxInitCount'
			}, {
				header : EwayLocale.monitor.devMonitor.cash.boxCurrentCount,
				dataIndex : 'boxCurrentCount'
			}, {
				header : EwayLocale.commen.devTypeName,
				dataIndex : 'type'
			}, {
				header : EwayLocale.commen.installAddr,
				dataIndex : 'address'
			}, {
				header : EwayLocale.commen.seviceMode,
				dataIndex : 'seviceMode'
			}, {
				header : EwayLocale.commen.insideOutside,
				dataIndex : 'insideOutside',
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true,
				displayMsg : EwayLocale.commen.toolbar
			})
		});

		this.callParent(arguments);
	}
});