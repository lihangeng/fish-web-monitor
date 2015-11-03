Ext.define('Eway.view.machine.device.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.device.view',

	requires : [ 'Eway.view.machine.device.Grid',
			'Eway.view.machine.device.FilterForm',
			'Eway.view.machine.device.TempGrid'
			  ],

	title : Eway.locale.machine.device.devManage,
	layout : 'border',
	isLoad : true,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'device_filterform'
			},{
				region : 'center',
				xtype: 'tabpanel',
				items : [{
					xtype: 'device_grid',
						title: Eway.locale.machine.device.efficientDev,
						minHeight:240,
				        flex : 1
					},{
						xtype: 'device_tempGrid',
						title:Eway.locale.machine.device.unEfficientDev,
						minHeight:240,
				        flex : 1

					}]
			}],
			listeners : {
				activate : function(panel) {
					if (!panel.isLoad) {

						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					// 刷新设备型号信息
					panel.down('device_filterform').down('field_device_deviceatmtype').getStore().load();
					// 刷新设备类型信息
					panel.down('device_filterform').down('field_atmType_DeviceAtmCatalogComboBox').getStore().load();
					// 刷新设备品牌信息
					panel.down('device_filterform').down('field_atmType_DeviceAtmVendorComboBox').getStore().load();

					var orgTrees = panel.down('device_filterform').query('common_orgComboOrgTree');
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