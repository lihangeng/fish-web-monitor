Ext.define('Eway.view.report.openrate.org.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.report_openrate_org_view',

	requires : [
			'Eway.view.report.openrate.FilterForm',
			'Eway.view.report.openrate.org.TreeGrid' ],

	title : '机构开机率',
	layout : 'border',

	isLoad : false,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				itemId : 'filterFormItemId',
				region : 'north',
				xtype : 'report_openrate_filterform'
			}, {
				itemId : 'gridItemId',
				region : 'center',
				xtype : 'report_openrate_org_treegrid'
			} ]
		});
		this.callParent(arguments);
	}
});