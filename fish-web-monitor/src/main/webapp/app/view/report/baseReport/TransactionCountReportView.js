Ext.define('Eway.view.report.baseReport.TransactionCountReportView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.transactionCountReportView',

	requires : [ 'Eway.view.report.baseReport.TransactionCountReportFilter',
			'Eway.view.report.baseReport.ReportDownload' ],

	title : EwayLocale.report.baseReport.tradeRep,
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'baseReport_TransactionCountReportFilter'
			}, {
				region : 'center',
				xtype : 'reportDownload'
			} ],
			listeners : {
				activate : function(panel) {
					if (!panel.isLoad) {
						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}

					var orgTrees = panel.down(
							'baseReport_TransactionCountReportFilter').query(
							'common_orgComboOrgTree');
					Ext.Array.each(orgTrees, function() {
						// 刷新维护商和所属机构信息
						this.reflesh();
					});
				}
			}
		});

		this.callParent(arguments);
	}
});