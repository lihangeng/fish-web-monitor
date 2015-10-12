Ext.define('Eway.view.report.openrate.device.Grid', {
	alias : 'widget.report_openrate_device_grid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	viewConfig:{
		forceFit: true,
		 scrollOffset: 0
	},
	initComponent : function() {
		var store = Ext.create('Eway.store.report.openrate.DeviceOpenRate');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : '统计',
				glyph : 0xf002,
				action : 'query'
			}, {
				text : '导出',
				iconCls : 'exportToExcel',
				action : 'importStat'
			} ],
			columns : [ {
				header : Eway.locale.commen.terminalId,
				dataIndex : 'terminalId',
				width:80
			}, {
				header : '统计日期',
				dataIndex : 'statDate',
				width:100
			}/*, {
				header : '方案开机时间',
				dataIndex : 'programOpenTime'
			}, {
				header : '方案关机时间',
				dataIndex : 'programCloseTime'
			}, {
				header : '方案应开机时长',
				dataIndex : 'programTimes'
			}, {
				header : '方案有效开机时长',
				dataIndex : 'programTimeReal'
			}*/, {
				header : '设备应工作时长',
				dataIndex : 'openTimes',
				width:140
			}, {
				header : '正常状态时长',
				dataIndex : 'healthyTimeReal',
				width:120
			}, {
				header : '管机员维护时长',
				dataIndex : 'maintainTimeReal',
				width:130
			}, {
				header : '离线未知时长',
				dataIndex : 'unknownTimeReal',
				width:120
			}, {
				header : '硬件故障停机时长',
				dataIndex : 'faultTimeReal',
				width:140
			}, {
				header : 'ATMP故障时长',
				dataIndex : 'atmpTimeReal',
				width:130
			}, {
				header : '其它暂停服务状态时长',
				dataIndex : 'stopTimeReal',
				width:180
			}, {
				header : '实际工作开机率',
				sortable : true,
				renderer : this.pctChange,
				dataIndex : 'openRate',
				width:130
//				flex:true
			}/*, {
				header : '方案开机率',
				sortable : true,
				renderer : this.pctChange,
				dataIndex : 'programOpenRate'
			}*/ ],
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
	/**
	 * Custom function used for column renderer
	 *
	 * @param {Object}
	 *            val
	 */
	pctChange : function(val) {
		if (val >= 20) {
			return '<span style="color:green;">' + val + '%</span>';
		} else if (val < 20) {
			return '<span style="color:red;">' + val + '%</span>';
		}
		return val;
	}
});