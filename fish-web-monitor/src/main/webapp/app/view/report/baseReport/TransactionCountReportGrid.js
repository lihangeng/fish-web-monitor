Ext.define('Eway.view.report.baseReport.TransactionCountReportGrid', {
	extend: 'Ext.panel.Panel',
	alias : 'widget.baseReport_TransactionCountReportGrid',
	requires : [ 'Eway.view.report.baseReport.ReportDownloadBody' ],

	scrollable :true,
	closable : false,
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'reportDownloadBody'
			} ],
			tbar : [ '->', {
				xtype : 'button',
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query',
				code : 'TransactionCountQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				xtype : 'button',
				text : EwayLocale.button.exportXLS,
				glyph : 0xf1c3,
				action : 'exportXls',
				code : 'TransactionCountExportXLS',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				xtype : 'button',
				text : EwayLocale.button.exportPDF,
				glyph : 0xf1c1,
				action : 'exportPdf',
				code:'TransactionCountExportPDF',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			} ]
		});

		this.callParent(arguments);
	}
});