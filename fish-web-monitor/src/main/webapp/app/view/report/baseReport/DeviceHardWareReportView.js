Ext.define('Eway.view.report.baseReport.DeviceHardWareReportView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.baseReport_DeviceHardWareReportView',

	requires : [ 'Eway.view.report.baseReport.ReportDownload',
			'Eway.view.report.baseReport.DeviceHardWareReportFilter' ],

	title : '系统硬件配置报表',
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'baseReport_DeviceHardWareReportFilter'
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
							'baseReport_DeviceHardWareReportFilter').query(
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