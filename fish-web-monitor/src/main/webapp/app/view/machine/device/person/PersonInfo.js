Ext.define('Eway.view.machine.device.person.PersonInfo', {
	extend : 'Ext.window.Window',
	alias : 'widget.machine_device_person_personInfo',
	title : Eway.locale.machine.device.devPerson,
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
					title : Eway.locale.button.orgAdmin,
					itemid : 'organizationItemID',
					xtype : 'machine_device_person_oGrid'
				},{
					title : Eway.locale.button.personTM,
					itemid : 'tubeMachineItemID',
					xtype : 'machine_device_person_tmGrid'
				}, {
					title : Eway.locale.machine.device.maintainPerson,
					itemid : 'maintainItemID',
					xtype : 'machine_device_person_tGrid'
				} , {
					title : Eway.locale.report.plan.openPlan,
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