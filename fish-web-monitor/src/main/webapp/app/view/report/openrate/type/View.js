Ext.define('Eway.view.report.openrate.type.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.report_openrate_type_view',

	requires : [
			'Eway.view.report.openrate.FilterForm',
			'Eway.view.report.openrate.type.Grid' ],

	title : '型号开机率',
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
				xtype : 'report_openrate_type_grid'
			} ]
		});
		this.callParent(arguments);
	}
});