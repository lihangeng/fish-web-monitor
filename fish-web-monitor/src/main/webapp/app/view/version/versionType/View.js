Ext.define('Eway.view.version.versionType.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.versionTypeView',

	requires : [ 'Eway.view.version.versionType.Grid',
			'Eway.view.version.versionType.FilterForm' ],

	title : '软件分类管理',
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
					region : 'north',
					xtype : 'versionType_filterForm'
				}, {
					region : 'center',
					xtype : 'versionType_grid'
			} ]
		});

		this.callParent(arguments);
	}
});