Ext.define('Eway.view.report.openrate.type.Grid', {
	alias : 'widget.report_openrate_type_grid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	autoFit : true,

	initComponent : function() {
		var store = Ext.create('Eway.store.report.openrate.TypeOpenRate');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : Eway.locale.report.openrate.device.statistics,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : Eway.locale.report.openrate.device.importStat,
				iconCls : 'exportToExcel',
				glyph : 0xf1c3,
				action : 'importStat'
			} ],
			columns : [ {
				header : Eway.locale.report.openrate.type.terminalId,
				width : 200,
				dataIndex : 'terminalId'
			}, {
				header : Eway.locale.report.openrate.device.statDate,
				dataIndex : 'statDate',
				width : 100
			}, {
				header : Eway.locale.report.openrate.device.openTimes,
				dataIndex : 'openTimes',
				width : 140
			}, {
				header : Eway.locale.report.openrate.device.healthyTimeReal,
				dataIndex : 'healthyTimeReal',
				width : 120
			}, {
				header : Eway.locale.report.openrate.device.maintainTimeReal,
				dataIndex : 'maintainTimeReal',
				width : 130
			}, {
				header : Eway.locale.report.openrate.device.unknownTimeReal,
				dataIndex : 'unknownTimeReal',
				width : 120
			}, {
				header : Eway.locale.report.openrate.device.faultTimeReal,
				dataIndex : 'faultTimeReal',
				width : 140
			}, {
				header : Eway.locale.report.openrate.device.atmpTimeReal,
				dataIndex : 'atmpTimeReal',
				width : 130
			}, {
				header : Eway.locale.report.openrate.device.stopTimeReal,
				dataIndex : 'stopTimeReal',
				width : 180
			}, {
				header : Eway.locale.report.openrate.device.openRate,
				sortable : true,
				renderer : this.pctChange,
				dataIndex : 'openRate'
			} ],
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