Ext.define('Eway.view.version.distribute.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.version_distributeView',

	requires : [ 'Eway.view.version.field.VersionTypeComboBoxAdd',
			'Eway.view.version.distribute.VersionPie',
			'Eway.view.version.distribute.FilterForm',
			'Eway.view.version.distribute.VersionStatusPie',
			'Eway.view.version.distribute.VersionStatusDetailGrid',
			'Ext.ux.layout.ResponsiveColumn'],

	title : EwayLocale.version.View.distributionPic,
	layout: 'responsivecolumn',
    scrollable : 'y',
    defaults: {
    	frame: true
    },
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
					xtype : 'version_pie',
			    	responsiveCls: 'big-50 small-100'
				}, {
					xtype : 'versionstatus_pie',
			    	responsiveCls: 'big-50 small-100'
				}, {
					xtype : 'version_distribute_grid',
			    	responsiveCls: 'big-100 small-100'
				} ]
		});

		this.callParent(arguments);
	}
});