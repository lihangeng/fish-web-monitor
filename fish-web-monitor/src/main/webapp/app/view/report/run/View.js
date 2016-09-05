Ext.define('Eway.view.report.run.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.report_run_view',

	requires : ['Eway.lib.Util',
			'Eway.view.report.run.FilterForm',
			'Eway.view.report.run.Grid'],

	title : EwayLocale.report.run.viewTitle,
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items: [{
				region: 'north',
				xtype: 'report_run_filterform'
			}, {
				region: 'center',
				xtype: 'report_run_grid'
		}]
	});

		this.callParent(arguments);
	}
});