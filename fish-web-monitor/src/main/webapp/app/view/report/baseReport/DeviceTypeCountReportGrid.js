Ext.define('Eway.view.report.baseReport.DeviceTypeCountReportGrid', {
	extend: 'Ext.pivot.Grid',
    xtype: 'outline-pivot-grid',
	alias : 'widget.baseReport_DeviceTypeCountReportGrid',
	requires : [ 'Eway.store.report.baseReport.DeviceVendorCountReport' ],

//	scrollable :true,
//	closable : false,
	collapsible: false,
    multiSelect: true,
    store: {
        type: 'deviceVendorCount'
    },
    selModel: {
        type: 'rowmodel'
    },
    
    
    // Set layout type to "outline". If this config is missing then the default layout is "outline"
    viewLayoutType: 'outline',

    // Set this to false if multiple dimensions are configured on leftAxis and
    // you want to automatically expand the row groups when calculations are ready.
    startRowGroupsCollapsed: false,

    // Configure the aggregate dimensions. Multiple dimensions are supported.
    aggregate: [{
        dataIndex:  'deviceCount',
        header:     'Sum of value',
        aggregator: 'sum',
        width:      90
    }],

    // Configure the left axis dimensions that will be used to generate the grid rows
    leftAxis: [{
        dataIndex:  'orgName',
        header:     'orgName',
        width:      80
    },{
        dataIndex:  'vendorName',
        header:     'vendorName',
        sortable:   false,
        width:      80
    },{
        dataIndex:  'devTypeName',
        header:     'devTypeName'
    }],

    /**
     * Configure the top axis dimensions that will be used to generate the columns.
     * When columns are generated the aggregate dimensions are also used. If multiple aggregation dimensions
     * are defined then each top axis result will have in the end a column header with children
     * columns for each aggregate dimension defined.
     */
//    topAxis: [],

//    tbar: [{
//        text: 'Collapsing',
//        menu: [{
//            text: 'Collapse all',
//            handler: 'collapseAll'
//        },{
//            text: 'Expand all',
//            handler: 'expandAll'
//        }]
//    },{
//        text: 'Subtotals position',
//        menu: {
//            defaults: {
//                xtype: 'menucheckitem',
//                group:  'subtotals',
//                checkHandler: 'subtotalsHandler'
//            },
//            items: [{
//                text: 'First',
//                checked: true
//            },{
//                text: 'Last'
//            },{
//                text: 'None'
//            }]
//        }
//    },{
//        text: 'Totals position',
//        menu: {
//            defaults: {
//                xtype: 'menucheckitem',
//                group:  'totals',
//                checkHandler: 'totalsHandler'
//            },
//            items: [{
//                text: 'First'
//            },{
//                text: 'Last',
//                checked: true
//            },{
//                text: 'None'
//            }]
//        }
//    }],


    
    
	initComponent : function() {
		
		 var me = this;

//	        me.width = me.profileInfo.width;

	        me.callParent();
		
//		Ext.apply(this, {
//			
//			items : [ {
//				xtype : 'reportDownloadBody'
//			} ],
//			tbar : [ '->', {
//				xtype : 'button',
//				text : EwayLocale.button.search,
//				glyph : 0xf002,
//				action : 'query',
//				code : 'deviceTypeQuery',
//				listeners:{
//					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
//				}
//			}/*
//				 * , { xtype : 'button', text : '导出HTML', iconCls : 'exportBtn',
//				 * action : 'exportHtml' }
//				 */, {
//				xtype : 'button',
//				text : EwayLocale.button.exportXLS,
//				glyph : 0xf1c3,
//				action : 'exportXls',
//				code : 'deviceTypeExportXLS',
//				listeners:{
//					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
//				}
//			}, {
//				xtype : 'button',
//				text : EwayLocale.button.exportPDF,
//				glyph : 0xf1c1,
//				action : 'exportPdf',
//				code : 'deviceTypeExportPDF',
//				listeners:{
//					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
//				}
//			} ]
//		});
//
//		this.callParent(arguments);
	}
});