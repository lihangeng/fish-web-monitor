Ext.define('Eway.view.report.openrate.org.TreeGrid', {
	alias : 'widget.report_openrate_org_treegrid',
	extend : 'Ext.tree.Panel',
	requires : [ 'Eway.lib.Util' ],
	border : false,
	autoFit : true,
	autoScroll : true,
	initComponent : function() {

		var store = Ext.create('Eway.store.report.openrate.OrgOpenRate');

		Ext.apply(this, {
			useArrows : true,
			rootVisible : true,
			store : store,
			multiSelect : true,
			tbar : [ '->', {
				text : '统计',
				glyph : 0xf002,
				action : 'query'
			}, {
				text : '导出',
				iconCls : 'sureBtn',
				action : 'importStat'
			} ],
			columns : [ {
				xtype : 'treecolumn',
				text : '机构',
				width : 200,
				sortable : true,
				dataIndex : 'terminalId'
			}, {
				header : '统计日期',
				dataIndex : 'statDate',
				width : 100
			}, {
				header : '设备应工作时长',
				dataIndex : 'openTimes',
				width : 140
			}, {
				header : '正常状态时长',
				dataIndex : 'healthyTimeReal',
				width : 120
			}, {
				header : '管机员维护时长',
				dataIndex : 'maintainTimeReal',
				width : 130
			}, {
				header : '离线未知时长',
				dataIndex : 'unknownTimeReal',
				width : 120
			}, {
				header : '硬件故障停机时长',
				dataIndex : 'faultTimeReal',
				width : 140
			}, {
				header : 'ATMP故障时长',
				dataIndex : 'atmpTimeReal',
				width : 130
			}, {
				header : '其它暂停服务状态时长',
				dataIndex : 'stopTimeReal',
				width : 180
			}, {
				header : '实际工作开机率',
				sortable : true,
				renderer : this.pctChange,
				dataIndex : 'openRate'
			} ]
		});
		this.callParent(arguments);
	},

	/**
	 * Custom function used for column renderer
	 * 
	 * @param {Object}
	 *            val
	 */
	pctChange : function(val) {
		if (val == -1) {
			return;
		}

		if (val >= 20) {
			return '<span style="color:green;">' + val + '%</span>';
		} else if (val < 20) {
			return '<span style="color:red;">' + val + '%</span>';
		}
		return val;
	}
});