Ext.define('Eway.view.machine.device.person.PersonInfo', {
	extend : 'Ext.window.Window',
	alias : 'widget.machine_device_person_personInfo',
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
					itemid : 'organizationItemID',
					xtype : 'machine_device_person_oGrid'
				},{
					title : '管机员',
					itemid : 'tubeMachineItemID',
					xtype : 'machine_device_person_tmGrid'
				}, {
					title : '维护员',
					itemid : 'maintainItemID',
					xtype : 'machine_device_person_tGrid'
				} , {
					title : '开机方案',
					itemid : 'devicePlanInfoID',
					xtype : 'device_planInfo_grid'
				} ]
			}
		});
		this.callParent(arguments);
	},
	onOver : function() {
		this.up('window').close();
	}
});