Ext.define('Eway.view.version.distribute.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.version_distributeView',

	requires : [ 'Eway.view.version.field.VersionTypeComboBoxAdd',
			'Eway.view.version.distribute.VersionPie',
			'Eway.view.version.distribute.FilterForm',
			'Eway.view.version.distribute.VersionStatusPie',
			'Eway.view.version.distribute.VersionStatusDetailGrid'],

	title : EwayLocale.version.View.distributionPic,
	layout: {
        type: 'table',
        columns: 2
    },
    
    scrollable : 'y',
    bodyStyle : 'padding: 10px 5px 0px 5px',
    defaults: {
    	frame: true,
    	margin: 10
    },
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
		    		width: 550,
					xtype : 'version_pie'
				}, {
		    		width: 550,
		            height: 350,
					xtype : 'versionstatus_pie'
				}, {
					colspan:2,
			    	width: 1120,
					xtype : 'version_distribute_grid'
				} ]
		});

		this.callParent(arguments);
	}
});