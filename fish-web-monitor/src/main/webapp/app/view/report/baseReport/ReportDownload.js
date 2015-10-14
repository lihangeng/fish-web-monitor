Ext.define('Eway.view.report.baseReport.ReportDownload', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.reportDownload',
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
				text : Eway.locale.button.search,
				glyph : 0xf002,
				action : 'query'
			}/*
				 * , { xtype : 'button', text : '导出HTML', iconCls : 'exportBtn',
				 * action : 'exportHtml' }
				 */, {
				xtype : 'button',
				text : Eway.locale.button.exportXLS,
				iconCls : 'exportBtn',
				action : 'exportXls'
			}, {
				xtype : 'button',
				text : Eway.locale.button.exportPDF,
				iconCls : 'exportBtn',
				action : 'exportPdf'
			} ]
		});

		this.callParent(arguments);
	}
});