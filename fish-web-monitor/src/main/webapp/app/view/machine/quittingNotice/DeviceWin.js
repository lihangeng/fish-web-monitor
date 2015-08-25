Ext.define('Eway.view.machine.quittingNotice.DeviceWin', {
	alias : 'widget.quittingNotice_deviceWin',
	extend : 'Ext.window.Window',

	modal : true,
	resizable : false,
	maximizable : true,
	constrainHeader : true,

	requires : [ 'Eway.view.machine.quittingNotice.DeviceFilterForm',
			'Eway.view.machine.quittingNotice.DeviceGrid' ],
	title : '选择需要报停的设备',
	width : 750,
	height : 400,
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'quittingNotice_DeviceFilterForm'
			}, {
				region : 'center',
				xtype : 'quittingNotice_DeviceGrid'
			} ]
		});
		this.callParent(arguments);
	}

});