Ext.define('Eway.view.machine.device.person.MonitorPersonInfo', {
	extend : 'Ext.window.Window',
	alias : 'widget.monitor_person_info',
	title : '设备人员信息',
	modal : true,
	constrainHeader : true,
	width : 700,
	height : 450,
	// maxHeight : 600,.
	layout : 'fit',
	maximizable : true,
	resizable : false,
	autoScroll : true,
	bodyStyle : 'padding: 10px 10px 30px 10px',
	requires : [ 'Eway.view.machine.device.person.TGrid',
			'Eway.view.machine.device.person.TmGrid',
			'Eway.view.machine.device.person.OGrid',
			'Eway.view.machine.device.person.DevicePlanInfoGrid'],
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'tabpanel',
				frame : true,
				items : [{
					title : '机构管理员',
					itemid : 'mOrganizationItemID',
					xtype : 'machine_device_person_oGrid'
				},{
					title : '管机员',
					itemid : 'mTubeMachineItemID',
					xtype : 'machine_device_person_tmGrid'
				}, {
					title : '厂商管理员',
					itemid : 'mMaintainItemID',
					xtype : 'machine_device_person_tGrid'
				}]
			}
		});
		this.callParent(arguments);
	},
	onOver : function() {
		this.up('window').close();
	}
});