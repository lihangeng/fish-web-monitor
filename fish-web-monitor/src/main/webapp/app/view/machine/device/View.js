Ext.define('Eway.view.machine.device.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.device.view',

	requires : [ 'Eway.view.machine.device.Grid',
			'Eway.view.machine.device.FilterForm' ],

	title : Eway.locale.machine.device.title,
	layout : 'border',

	isLoad : false,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				itemId : 'filterFormItemId',
				region : 'north',
				//height：40,
				xtype : 'device_filterform'
			}, {
				itemId : 'gridItemId',
				region : 'center',
				xtype : 'device_grid'
			} ],

			listeners : {
				activate : function(panel) {
					if (!panel.isLoad) {

						// 第一次进来不需要重新加载信息
						panel.isLoad = true;
						return;
					}
					// 刷新设备型号信息
//					panel.down('device_filterform').down('field_device_deviceatmtype').getStore().load();
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
