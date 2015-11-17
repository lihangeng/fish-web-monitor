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
				text : EwayLocale.report.openrate.device.statistics,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : EwayLocale.report.openrate.device.importStat,
				glyph : 0xf1c3,
				action : 'importStat'
			} ],
			columns : [ {
				xtype : 'treecolumn',
				text : EwayLocale.report.openrate.device.organizationName,
				width : 200,
				sortable : true,
				dataIndex : 'terminalId'
			}, {
				header : EwayLocale.report.openrate.device.statDate,
				dataIndex : 'statDate',
				width : 100
			}, {
				header : EwayLocale.report.openrate.device.openTimes,
				dataIndex : 'openTimes',
				width : 140
			}, {
				header : EwayLocale.report.openrate.device.healthyTimeReal,
				dataIndex : 'healthyTimeReal',
				width : 120
			}, {
				header : EwayLocale.report.openrate.device.maintainTimeReal,
				dataIndex : 'maintainTimeReal',
				width : 130
			}, {
				header : EwayLocale.report.openrate.device.unknownTimeReal,
				dataIndex : 'unknownTimeReal',
				width : 120
			}, {
				header : EwayLocale.report.openrate.device.faultTimeReal,
				dataIndex : 'faultTimeReal',
				width : 140
			}, {
				header : EwayLocale.report.openrate.device.atmpTimeReal,
				dataIndex : 'atmpTimeReal',
				width : 130
			}, {
				header : EwayLocale.report.openrate.device.stopTimeReal,
				dataIndex : 'stopTimeReal',
				width : 180
			}, {
				header : EwayLocale.report.openrate.device.openRate,
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