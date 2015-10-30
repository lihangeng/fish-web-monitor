Ext.define('Eway.view.version.distribute.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.version_distributeView',

	requires : [ 'Eway.view.version.field.VersionTypeComboBoxAdd',
			'Eway.view.version.distribute.VersionPie',
			'Eway.view.version.distribute.FilterForm',
			'Eway.view.version.distribute.VersionStatusPie',
			'Eway.view.version.distribute.VersionStatusDetailGrid'],

	title : Eway.locale.version.View.distributionPic,
	initComponent : function() {
		Ext.apply(this, {
			padding:'5 0 0 0',
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