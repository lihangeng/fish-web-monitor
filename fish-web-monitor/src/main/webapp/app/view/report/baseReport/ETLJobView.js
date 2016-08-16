Ext.define('Eway.view.report.baseReport.ETLJobView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.trans_JobView',

	requires : [ 'Eway.view.report.baseReport.ETLJobFilter',
			'Eway.view.report.baseReport.ETLJobGrid' ],

	title : EwayLocale.batch.tradeRes,
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'trans_JobFilter'
			}, {
				region : 'center',
				xtype : 'trans_JobGrid'
			}]
		});

		this.callParent(arguments);
	}
});