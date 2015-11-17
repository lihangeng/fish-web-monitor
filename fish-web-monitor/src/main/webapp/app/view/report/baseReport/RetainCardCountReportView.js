Ext.define('Eway.view.report.baseReport.RetainCardCountReportView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.retainCardCountReportView',

	requires : [ 'Eway.view.report.baseReport.RetainCardCountReportFilter',
			'Eway.view.report.baseReport.ReportDownload' ],

	title : EwayLocale.report.baseReport.eatCardRep,
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'baseReport_RetainCardCountReportFilter'
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
							'baseReport_RetainCardCountReportFilter').query(
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