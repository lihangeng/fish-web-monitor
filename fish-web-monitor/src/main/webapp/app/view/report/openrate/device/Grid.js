Ext.define('Eway.view.report.openrate.device.Grid', {
	alias : 'widget.report_openrate_device_grid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],
	
	  viewConfig : {
	        forceFit : true,
	        enableTextSelection : true
//	        getRowClass : function(record, index) {
//
//	            var openRate = record.get('openRate');
//	            var avgOpenRate = record.get('avgOpenRate');
//
//	            var result = '';
//	            if (openRate<avgOpenRate) {
//	                result = 'device-openrate-report-red';
//	            }else{
//	            	result = 'device-openrate-report-green';
//	            }
//
////	            if (localRet) {
////	                result += ' jsnx-grid-local-' + localRet;
////	            }
//
//	            return result;
//	        }
	    },

	border : false,
	initComponent : function() {
		var store = Ext.create('Eway.store.report.openrate.DeviceOpenRate');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : EwayLocale.report.openrate.device.statistics,
				glyph : 0xf002,
				action : 'query',
				code:'deviceOpenrateCountQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.report.openrate.device.importStat,
				glyph : 0xf1c3,
				action : 'importStat',
				code:'deviceOpenrateCountImport',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			} ],
			columns : [ {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId',
				width:80
			}, {
				header : EwayLocale.commen.orgNameBelongs,
				dataIndex : 'orgName',
				width:150,
				align:'center'
			},{
				header : EwayLocale.commen.devCatalogName,
				dataIndex : 'devCatalogName',
				align:'center'
			},{
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
				width:120
			}, {
				header : EwayLocale.report.openrate.device.healthyTimeReal,
				dataIndex : 'healthyTimeReal',
				width:100
			}, {
				header : EwayLocale.report.openrate.device.maintainTimeReal,
				dataIndex : 'maintainTimeReal',
				width:100
			}, {
				header : EwayLocale.report.openrate.device.unknownTimeReal,
				dataIndex : 'unknownTimeReal',
				width:100
			}, {
				header : EwayLocale.report.openrate.device.faultTimeReal,
				dataIndex : 'faultTimeReal',
				width:100
			}, {
				header : EwayLocale.report.openrate.device.atmpTimeReal,
				dataIndex : 'atmpTimeReal',
				width:100
			}, {
				header : EwayLocale.report.openrate.device.stopTimeReal,
				dataIndex : 'stopTimeReal',
				width:150
			}, {
				header : EwayLocale.report.openrate.device.openRate,
				sortable : true,
				renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			         var avgOpenRate = record.get('avgOpenRate');
			         if (value >= avgOpenRate) {
			 			return '<span style="color:green;">' + value + '%</span>';
			 		 } else if (value < avgOpenRate) {
			 			return '<span style="color:red;">' + value + '%</span>';
			 		 }
				},
				dataIndex : 'openRate',
				width:100
//				flex:true
			}
			
			/*, {
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
		var avgOpenRate = record.get('avgOpenRate');
		if (val >= avgOpenRate) {
			return '<span style="color:green;">' + val + '%</span>';
		} else if (val < avgOpenRate) {
			return '<span style="color:red;">' + val + '%</span>';
		}
		return val;
	}
});
