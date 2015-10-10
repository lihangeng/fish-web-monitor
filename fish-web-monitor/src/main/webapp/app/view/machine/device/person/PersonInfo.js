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
			'Eway.view.machine.device.person.TmGrid' ],
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'tabpanel',
				frame : true,
				items : [ {
					title : Eway.locale.commen.comboxType.machineManager,
					itemid : 'tubeMachineItemID',
					xtype : 'machine_device_person_tmGrid'
				}, {
					title : '维护员',
					itemid : 'maintainItemID',
					xtype : 'machine_device_person_tGrid'
				} ]
			}
		});
		this.callParent(arguments);
	},
	onOver : function() {
		this.up('window').close();
	}
});