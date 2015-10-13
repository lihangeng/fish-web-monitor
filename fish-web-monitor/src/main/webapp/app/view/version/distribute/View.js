Ext.define('Eway.view.version.distribute.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.version_distributeView',

	requires : [ 'Eway.view.version.field.VersionTypeComboBoxAdd',
			'Eway.view.version.distribute.VersionPie',
			'Eway.view.version.distribute.FilterForm',
			'Eway.view.version.distribute.VersionStatusPie',
			'Eway.view.version.distribute.VersionStatusDetailGrid'],

	title : '版本分布图',
	initComponent : function() {
		Ext.apply(this, {
			padding:'10 0 10 0',
			layout : {
				type : 'border'
			},
			items : [ {
				region : 'west',
				items : [ {
					region : 'north',
					xtype : 'distribute_filterForm'
				}, {
					region : 'center',
					xtype : 'version_pie'
				} ]
				}, {
					region : 'center',
					xtype : 'versionstatus_pie'
				}, {
					region : 'south',
					xtype : 'version_distribute_grid'
				} ]
		});

		this.callParent(arguments);
	}
});