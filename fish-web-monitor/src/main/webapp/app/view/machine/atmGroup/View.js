Ext.define('Eway.view.machine.atmGroup.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.machine_atmGroup_view',

	requires : [ 'Eway.view.machine.atmGroup.GroupGrid',
	             'Eway.view.machine.atmGroup.DeviceFilter',
			'Eway.view.machine.atmGroup.DeviceGrid' ],

	title : EwayLocale.machine.atmGroup.devGroupName,
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region: 'center',
				xtype: 'tabpanel',
				plain:true,
				tabPosition : 'bottom',
				headerPosition: 'bottom',
				split: true,
				items : [{
						name:'groupPanel',
						xtype:'panel',
						title: EwayLocale.machine.atmGroup.groupName,
						layout : 'border',
						items:[{
							xtype:'form',
							region: 'north',
							height : 40,
							items:[{
								labelAlign : 'right',
								xtype : 'textfield',
								style :'padding-top:10px',
								name : 'name',
								maxLength : 30,
								fieldLabel : EwayLocale.machine.atmGroup.groupName,
								msgTarget : 'side'
							}]}, {
								region: 'center',
								xtype : 'atmGroup_groupGrid'
							}
						]}
				]
			}]
		});

		this.callParent(arguments);
	}
});