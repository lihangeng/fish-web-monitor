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
				text : EwayLocale.report.openrate.device.statistics,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : EwayLocale.report.openrate.device.importStat,
				glyph : 0xf1c3,
				action : 'importStat'
			} ],
			columns : [ {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId',
				width:80
			}, {
				header : EwayLocale.report.openrate.device.statDate,
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
				header : EwayLocale.report.openrate.device.openTimes,
				dataIndex : 'openTimes',
				width:140
			}, {
				header : EwayLocale.report.openrate.device.healthyTimeReal,
				dataIndex : 'healthyTimeReal',
				width:120
			}, {
				header : EwayLocale.report.openrate.device.maintainTimeReal,
				dataIndex : 'maintainTimeReal',
				width:130
			}, {
				header : EwayLocale.report.openrate.device.unknownTimeReal,
				dataIndex : 'unknownTimeReal',
				width:120
			}, {
				header : EwayLocale.report.openrate.device.faultTimeReal,
				dataIndex : 'faultTimeReal',
				width:140
			}, {
				header : EwayLocale.report.openrate.device.atmpTimeReal,
				dataIndex : 'atmpTimeReal',
				width:130
			}, {
				header : EwayLocale.report.openrate.device.stopTimeReal,
				dataIndex : 'stopTimeReal',
				width:180
			}, {
				header : EwayLocale.report.openrate.device.openRate,
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