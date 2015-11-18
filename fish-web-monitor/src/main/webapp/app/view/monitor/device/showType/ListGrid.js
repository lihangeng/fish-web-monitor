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
				header : EwayLocale.person.bankOrg.name,
				dataIndex : 'org'
			}, {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'code',
				width : 100,
				tdCls : 'pointerLink'
			}, {
				header : EwayLocale.commen.ip,
				dataIndex : 'ip',
				width : 100
			}, {
				header : EwayLocale.monitor.devMonitor.comboxStatus.runStatus,
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
				header : EwayLocale.monitor.devMonitor.comboxStatus.modStatus,
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
				header : EwayLocale.monitor.devMonitor.comboxStatus.boxStatus,
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
				header : EwayLocale.monitor.devMonitor.comboxStatus.netStatus,
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
				header : EwayLocale.monitor.devMonitor.retainCardCount,
				dataIndex : 'retainCardCount',
				width : 100
			}, {
				header : EwayLocale.monitor.devMonitor.cash.boxInitCount,
				dataIndex : 'boxInitCount',
				width : 100
			}, {
				header : EwayLocale.monitor.devMonitor.cash.boxCurrentCount,
				dataIndex : 'boxCurrentCount',
				width : 100
			}, {
				header : EwayLocale.commen.devTypeName,
				dataIndex : 'type',
				width : 100
			}, /*{
				header : EwayLocale.commen.orgNameBelongs,
				dataIndex : 'org',
				flex : 1
			}, */{
				header : EwayLocale.commen.installAddr,
				dataIndex : 'address'
			}, {
				header : EwayLocale.commen.seviceMode,
				dataIndex : 'seviceMode',
				width : 100
			}, {
				header : EwayLocale.commen.insideOutside,
				dataIndex : 'insideOutside',
				minWidth : 150,
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true,
				items : ['-', EwayLocale.tip.formatPageBfMsg, {
				    xtype : 'combobox',
				    name: 'pagesize',
			        hiddenName: 'pagesize',
			        store: new Ext.data.ArrayStore({
			            fields: ['text', 'value'],
			            data: [['25', 25], ['50', 50],['100', 100], ['200', 200]]
			        }),
			        valueField : 'value',
			        displayField : 'text',
			        value : 25,
			        width: 60,
			        editable : false,
			        listeners : {
				        change : function( This, newValue, oldValue, eOpts ) {
        				    var pagingToolbar = This.up('pagingtoolbar');

        				    var itemsPerPage = parseInt(newValue);//更改全局变量itemsPerPage

        				    var store = pagingToolbar.getStore();

        				    store.pageSize = itemsPerPage;//设置store的pageSize，可以将工具栏与查询的数据同步。

        				    store.loadPage(1);//显示第一页
				        }
				    }
				}, EwayLocale.tip.formatPageAfMsg]
			})
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});