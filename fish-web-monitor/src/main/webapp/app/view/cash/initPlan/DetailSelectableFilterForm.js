Ext.define('Eway.view.cash.initPlan.DetailSelectableFilterForm',{
	extend: 'Eway.view.base.FilterForm',
	alias : 'widget.initPlan_detailSelectableFilterForm',
	requires : [ 'Eway.view.common.OrgComboOrgTree', 'Eway.view.field.device.DeviceAtmType',
	             'Eway.lib.Util'],
	closable:false,
	layout : 'column',
	height: 'auto',
	
	initComponent : function(){
		Ext.apply(this,{
			items: [ {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right'
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : EwayLocale.machine.atmGroup.terminalId,
					name : 'terminalId',
					allowBlank : true,
					msgTarget : 'side'
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'cashInitPlanInfoId'
				},{
					xtype : 'field_device_deviceatmtype',
					name:'devType',
					emptyText :  EwayLocale.combox.select
				},{
			        xtype: 'hidden',
			        name: 'cashInitPlanInfoId'
				},{
			        xtype: 'hidden',
			        name: 'terminalIds'
				}]
			}, {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right',
					labelWidth:160
				},
				items : [{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organizationID',
					value : Eway.user.getOrgId()
				}, {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'orgId'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.machine.atmGroup.orgName,
					emptyText : EwayLocale.combox.select,
					name : 'orgName',
					hiddenValue : 'orgId',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : Eway.user.getOrgType() != "" && Eway.user.getOrgType() == '0' ? true : false
				},{
					xtype : 'combobox',
					fieldLabel : EwayLocale.machine.device.onBankSignal,
					emptyText :  EwayLocale.combox.select,
					name : 'awayFlag',
					hiddenName : 'awayFlag',
					editable : false,
					store: 'machine.DeviceAwayFlagComboBox',
					valueField : 'value',
					displayField : 'display',
					queryMode : 'local'
			}]
			} ]
		});
		this.callParent(arguments);
	}
	
});