Ext.define('Eway.view.report.openrate.type.Grid', {
	alias : 'widget.report_openrate_type_grid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	autoFit : true,

	initComponent : function() {
		var store = Ext.create('Eway.store.report.openrate.TypeOpenRate');		
		Ext.apply(this, {
			initRegion : true,
			store : store,
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
				header : EwayLocale.report.openrate.type.terminalId,
				width : 150,
				dataIndex : 'terminalId'
			}, {
				header : EwayLocale.report.openrate.device.statDate,
				dataIndex : 'statDate',
				width : 100
			}, {
				header : EwayLocale.report.openrate.device.openTimes,
				dataIndex : 'openTimes',
				width : 120
			}, {
				header : EwayLocale.report.openrate.device.healthyTimeReal,
				dataIndex : 'healthyTimeReal',
				width : 100
			}, {
				header : EwayLocale.report.openrate.device.maintainTimeReal,
				dataIndex : 'maintainTimeReal',
				width : 100
			}, {
				header : EwayLocale.report.openrate.device.unknownTimeReal,
				dataIndex : 'unknownTimeReal',
				width : 100
			}, {
				header : EwayLocale.report.openrate.device.faultTimeReal,
				dataIndex : 'faultTimeReal',
				width : 100
			}, {
				header : EwayLocale.report.openrate.device.atmpTimeReal,
				dataIndex : 'atmpTimeReal',
				width : 100
			}, {
				header : EwayLocale.report.openrate.device.stopTimeReal,
				dataIndex : 'stopTimeReal',
				width : 120
			}, {
				header : EwayLocale.report.openrate.device.openRate,
				sortable : true,
				renderer : this.pctChange,
				dataIndex : 'openRate',
				flex:1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true,
				displayMsg : EwayLocale.commen.toolbar
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