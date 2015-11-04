Ext.define('Eway.view.machine.atmGroup.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.machine_atmGroup_view',

	requires : [ 'Eway.view.machine.atmGroup.GroupGrid',
	             'Eway.view.machine.atmGroup.DeviceFilter',
			'Eway.view.machine.atmGroup.DeviceGrid' ],

	title : Eway.locale.machine.atmGroup.devGroupName,
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
						title: Eway.locale.machine.atmGroup.devGroupName,
						layout : 'border',
						items:[{
							xtype:'form',
							region: 'north',
							height : 40,
							items:[{
								labelAlign : 'right',
								labelWidth : 50,
								xtype : 'textfield',
								name : 'name',
								maxLength : 30,
								fieldLabel : Eway.locale.machine.atmGroup.groupName,
								msgTarget : 'side'
							}]}, {
								region: 'center',
								xtype : 'atmGroup_groupGrid'
							}
						]},
						{
						name:'atmGroupDeviceDetails',
						disabled:true,
						title:Eway.locale.machine.device.title,
						layout : 'border',
						xtype:'panel',
						items:[{
								region: 'north',
								xtype : 'atmGroup_deviceFilter'
							}, {
								region: 'center',
								xtype : 'atmGroup_deviceGrid'
							}
						]} 
				]
			}],
			listeners : {
				activate : function(panel){
					if (!panel.isLoad) {
						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					panel.down('atmGroup_deviceFilter').down('field_device_deviceatmtype').getStore().load();
					panel.down('atmGroup_deviceFilter').down('field_atmType_DeviceAtmCatalogComboBox').getStore().load();
					panel.down('atmGroup_deviceFilter').down('field_atmType_DeviceAtmVendorComboBox').getStore().load();
					//刷新组织机构
					var orgTrees = panel.down('atmGroup_deviceFilter').query('common_orgComboOrgTree');
					Ext.Array.each(orgTrees, function() {
						// 刷新维护商和所属机构信息
						this.reflesh();
					});
				}
			}
		});

		this.callParent(arguments);
	}
});