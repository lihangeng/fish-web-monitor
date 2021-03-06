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
				header : EwayLocale.machine.atmGroup.orgName,
				dataIndex : 'org',
				flex:1
			}, {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'code',
				width:120,
				tdCls : 'pointerLink'
			}, {
				header : EwayLocale.monitor.devMonitor.comboxStatus.boxStatus,
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
				width:120,
				tdCls : 'pointerLink'
			}, {
				header : EwayLocale.monitor.devMonitor.cash.boxInitCount,
				dataIndex : 'boxInitCount',
				width:180
			}, {
				header : EwayLocale.monitor.devMonitor.cash.boxCurrentCount,
				dataIndex : 'boxCurrentCount',
				width:180
			}, {
				header : EwayLocale.monitor.devMonitor.cash.cashboxLimit,
				dataIndex : 'cashboxLimit',
				width:170
			}, {
				header : EwayLocale.monitor.devMonitor.retainCardCount,
				dataIndex : 'retainCardCount',
			 	renderer : function(value, meta, record) { 
			 		if(value >= 20) { 
			 			return "<span class='fatalHighLight'>"+ value + "</span>"; 
		 			} else { 
		 				return value; 
	 				} 
 				},
 				width:150
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