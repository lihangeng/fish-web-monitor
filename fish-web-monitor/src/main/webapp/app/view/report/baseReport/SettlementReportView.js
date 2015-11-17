Ext.define('Eway.view.report.baseReport.SettlementReportView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.settlementReportView',

	requires : [ 'Eway.view.report.baseReport.SettlementReportFilter',
			'Eway.view.report.baseReport.ReportDownload' ],

	title : EwayLocale.report.baseReport.clearTable,
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'baseReport_SettlementReportFilter'
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

					// 刷新设备品牌信息
					panel.down('baseReport_SettlementReportFilter').down(
							'field_device_deviceatmtype').getStore().load();
					var orgTrees = panel.down(
							'baseReport_SettlementReportFilter').query(
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