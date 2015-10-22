Ext.define('Eway.view.version.distribute.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.distribute_filterForm',
	requires:['Eway.view.version.field.VersionTypeComboBoxAdd',
	          'Eway.view.common.OrgComboOrgTree'],
	height : 50,
	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			items : [{
				columnWidth : .5,
				items : [ {
					xtype:'field_versionTypeComboBoxAdd',
					fieldLabel : '',
					selectFirst:true,
					name:'versionType'
				}]}
			 ]
		});
		this.callParent(arguments);
	}

});