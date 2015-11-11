Ext.define('Eway.view.monitor.charts.MonitorDeviceGrid',{
	extend : 'Ext.grid.Panel',
	alias : 'widget.monitor_device_grid',

	scrollable:'both',
	initComponent : function() {
		var store = Ext.create('Eway.store.monitor.charts.DonutChartsDetail', {});
		Ext.apply(this, {
			store : store,
			columns : [  {
				header : Eway.locale.commen.terminalId,
				dataIndex : 'code',
				width:80
			},  {
				header : Eway.locale.monitor.devMonitor.comboxStatus.runStatus,
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
				header : Eway.locale.monitor.devMonitor.comboxStatus.modStatus,
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
				header : Eway.locale.monitor.devMonitor.comboxStatus.boxStatus,
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
				header : Eway.locale.monitor.devMonitor.comboxStatus.netStatus,
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
				header : Eway.locale.commen.ip,
				dataIndex : 'ip'
			}, {
				header : Eway.locale.person.bankOrg.name,
				dataIndex : 'org'
			}, {
				header : Eway.locale.monitor.devMonitor.retainCardCount,
				dataIndex : 'retainCardCount'
			}, {
				header : Eway.locale.monitor.devMonitor.cash.boxInitCount,
				dataIndex : 'boxInitCount'
			}, {
				header : Eway.locale.monitor.devMonitor.cash.boxCurrentCount,
				dataIndex : 'boxCurrentCount'
			}, {
				header : Eway.locale.commen.devTypeName,
				dataIndex : 'type'
			}, {
				header : Eway.locale.commen.installAddr,
				dataIndex : 'address',
				flex : 1
			}, {
				header : Eway.locale.commen.seviceMode,
				dataIndex : 'seviceMode'
			}, {
				header : Eway.locale.commen.insideOutside,
				dataIndex : 'insideOutside'
			} ],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true,
				displayMsg : Eway.locale.commen.toolbar
			})
		});

		this.callParent(arguments);
	}
});