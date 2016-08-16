Ext.define('Eway.view.report.baseReport.ETLCountView', {
	extend : 'Ext.window.Window',
	alias : 'widget.trans_CountView',

	requires : ['Eway.view.report.baseReport.ETLCountGrid' ],
	title : EwayLocale.batch.unOperaCount,
	layout : 'border',
	width : 800,
	height : 600,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'center',
				xtype : 'trans_CountGrid'
			}]
		});

		this.callParent(arguments);
	}
});