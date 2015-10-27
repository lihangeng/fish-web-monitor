/**
 * 人员设备关联显示窗口：
 */
Ext.define('Eway.view.operatingPlan.PlanDevice', {
	alias: 'widget.operatingPlan_planDevice',
	extend: 'Ext.window.Window',

	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,

	requires: ['Eway.view.operatingPlan.LinkedDevicePanel',
	           'Eway.view.operatingPlan.LinkingDevicePanel'],

	width: 1040,
	height: 600,
	layout: 'border',

	initComponent: function() {
		Ext.apply(this, {
			title : '方案<-->设备',
			items : [{
				region: 'west',
				width:'50%',
				padding : 1,
				xtype : 'operatingPlan_linkedDevicePanel',
				//autoLoadStore : true
			},{
				region: 'center',
				width:'50%',
				padding : 1,
				xtype : 'operatingPlan_linkingDevicePanel',
				//autoLoadStore : true
			}]
		});
		this.callParent(arguments);
	}

});