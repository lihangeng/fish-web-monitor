Ext.define('Eway.view.report.baseReport.DeviceReportView', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.baseReport_DeviceReportView',

	requires : [ 'Eway.view.report.baseReport.ReportDownload',
			'Eway.view.report.baseReport.DeviceReportFilter' ],

	title : Eway.locale.report.baseReport.devDetailRep,
	layout : 'border',

	isLoad : false,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'baseReport_DeviceReportFilter'
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

					var orgTrees = panel.down('baseReport_DeviceReportFilter')
							.query('common_orgComboOrgTree');
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