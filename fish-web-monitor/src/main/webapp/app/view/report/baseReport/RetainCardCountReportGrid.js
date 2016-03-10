Ext.define('Eway.view.report.baseReport.RetainCardCountReportGrid', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.baseReport_RetainCardCountReportGrid',
	requires : [ 'Eway.view.report.baseReport.ReportDownloadBody' ],

	layout : 'border',
	closable : false,
	initComponent : function() {
		Ext.apply(this, {
			xtype : 'panel',
			layout : 'border',
			items : [ {
				region : 'center',
				xtype : 'reportDownloadBody'
			} ],
			tbar : [ '->', {
				xtype : 'button',
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query',
				code : 'retainCardQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				xtype : 'button',
				text : EwayLocale.button.exportXLS,
				glyph : 0xf1c3,
				action : 'exportXls',
				code : 'retainCardExportXLS',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				xtype : 'button',
				text : EwayLocale.button.exportPDF,
				glyph : 0xf1c1,
				action : 'exportPdf',
				code:'retainCardExportPDF',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			} ]
		});

		this.callParent(arguments);
	}
});