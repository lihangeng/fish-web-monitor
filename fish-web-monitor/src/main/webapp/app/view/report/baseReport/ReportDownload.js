Ext.define('Eway.view.report.baseReport.ReportDownload', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.reportDownload',
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
				action : 'query'
			}/*
				 * , { xtype : 'button', text : '导出HTML', iconCls : 'exportBtn',
				 * action : 'exportHtml' }
				 */, {
				xtype : 'button',
				text : EwayLocale.button.exportXLS,
				glyph : 0xf1c3,
				action : 'exportXls'
			}, {
				xtype : 'button',
				text : EwayLocale.button.exportPDF,
				glyph : 0xf1c1,
				action : 'exportPdf'
			} ]
		});

		this.callParent(arguments);
	}
});