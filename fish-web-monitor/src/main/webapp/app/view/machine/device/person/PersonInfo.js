Ext.define('Eway.view.machine.device.person.PersonInfo', {
	extend : 'Ext.window.Window',
	alias : 'widget.machine_device_person_personInfo',
	title : EwayLocale.machine.device.devPerson,
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
	
	// 设备号,用来查看哪能设备的人员信息
	_terminalId : '',
	
	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'tabpanel',
				frame : true,
				items : [{
					title : EwayLocale.button.orgAdmin,
					itemid : 'organizationItemID',
					xtype : 'machine_device_person_oGrid',
					listeners : {
						render : function( tab ) {
							// 加载维护员的数据
							var params = {
								terminalId : this._terminalId
							};
							tab.onReload(params);
						},
						scope : this
					}
				}, {
					title : EwayLocale.button.personTM,
					itemid : 'tubeMachineItemID',
					xtype : 'machine_device_person_tmGrid',
					listeners : {
						render : function( tab ) {
							// 加载维护员的数据
							var params = {
								terminalId : this._terminalId,
								type : 0
							};
							tab.onReload(params);
						},
						scope : this
					}
				}, {
					title : EwayLocale.machine.device.maintainPerson,
					itemid : 'maintainItemID',
					xtype : 'machine_device_person_tGrid',
					listeners : {
						render : function( tab ) {
							// 加载维护员的数据
							var params = {
								terminalId : this._terminalId,
								type : 1
							};
							tab.onReload(params);
						},
						scope : this
					}
				} , {
					title : EwayLocale.report.plan.openPlan,
					itemid : 'devicePlanInfoID',
					xtype : 'device_planInfo_grid'
				} ]
			}
		});
		this.callParent(arguments);
	},
	
	onOver : function() {
		this.up('window').close();
	},
	
	setTerminalId : function(terminalId) {
		this._terminalId = terminalId;
	}
});