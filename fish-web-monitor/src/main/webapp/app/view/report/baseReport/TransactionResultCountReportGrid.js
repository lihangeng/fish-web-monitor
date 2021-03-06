Ext.define('Eway.view.report.baseReport.TransactionResultCountReportGrid', {
	extend: 'Ext.panel.Panel',
	alias : 'widget.baseReport_TransactionResultCountReportGrid',
	requires : [ 'Eway.view.report.baseReport.ReportDownloadBody' ],

	scrollable :true,
	closable : false,
	initComponent : function() {
		Ext.apply(this, {
			xtype : 'panel',
			items : [ {
				xtype : 'reportDownloadBody'
			} ],
			tbar : [ '->', {
				xtype : 'button',
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query',
				code : 'TransactionResultCountQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				xtype : 'button',
				text : EwayLocale.button.exportXLS,
				glyph : 0xf1c3,
				action : 'exportXls',
				code : 'TransactionResultCountExportXLS',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				xtype : 'button',
				text : EwayLocale.button.exportPDF,
				glyph : 0xf1c1,
				action : 'exportPdf',
				code:'TransactionResultCountExportPDF',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			} ]
		});

		this.callParent(arguments);
	}
});