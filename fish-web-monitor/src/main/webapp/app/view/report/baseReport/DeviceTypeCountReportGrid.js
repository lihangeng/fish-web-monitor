Ext.define('Eway.view.report.baseReport.DeviceTypeCountReportGrid', {
	extend: 'Ext.panel.Panel',
	alias : 'widget.baseReport_DeviceTypeCountReportGrid',
	requires : [ 'Eway.view.report.baseReport.ReportDownloadBody' ],

	layout : 'border',
	closable : false,
	initComponent : function() {
		Ext.apply(this, {
//			xtype : 'panel',
//			layout : 'border',
			items : [ {
				region : 'center',
				xtype : 'reportDownloadBody'
			} ],
			tbar : [ '->', {
				xtype : 'button',
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query',
				code : 'deviceTypeQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}/*
				 * , { xtype : 'button', text : '导出HTML', iconCls : 'exportBtn',
				 * action : 'exportHtml' }
				 */, {
				xtype : 'button',
				text : EwayLocale.button.exportXLS,
				glyph : 0xf1c3,
				action : 'exportXls',
				code : 'deviceTypeExportXLS',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				xtype : 'button',
				text : EwayLocale.button.exportPDF,
				glyph : 0xf1c1,
				action : 'exportPdf',
				code : 'deviceTypeExportPDF',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			} ]
		});

		this.callParent(arguments);
	}
});