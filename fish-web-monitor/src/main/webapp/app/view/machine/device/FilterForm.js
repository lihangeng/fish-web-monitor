Ext.define('Eway.view.machine.device.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.device_filterform',
	requires : [ 'Eway.view.common.OrgComboOrgTree', 'Eway.view.field.device.DeviceAtmType',
	             'Eway.view.field.atmType.DeviceAtmVendorComboBox',
	             'Eway.view.field.atmType.DeviceAtmCatalogComboBox',
	             'Eway.lib.Util'],
	height : 100,
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .28,
				defaults : {
					labelAlign : 'right',
					labelWidth : 80
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : EwayLocale.machine.atmGroup.terminalId,
					name : 'terminalId',
					vtype : 'terminalId',
					allowBlank : true,
					maxLength:20,
					msgTarget : 'side'
				}, {

					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organizationID',
					value : ewayUser.getOrgId()
				}, {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organization'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.machine.atmGroup.orgName,
					emptyText : EwayLocale.combox.select,
					name : 'orgName',
					hiddenValue : 'organization',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				}, {
					xtype : 'field_atmType_DeviceAtmVendorComboBox',
					fieldLabel : EwayLocale.machine.atmGroup.devVendorName,
				}]
			}, {
				columnWidth : .28,
				defaults : {
					labelAlign : 'right',
					labelWidth : 80
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel :  EwayLocale.machine.atmGroup.ip,
					name : 'ip',
					regex : /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,
					regexText : EwayLocale.vtype.ip,
					msgTarget : 'side'
				}, {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'devService'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.machine.atmGroup.devServiceName,
					emptyText : EwayLocale.combox.select,
					name : 'devServiceName',
					hiddenValue : 'devService',
					editable : false,
					filters : '{"type" : "1"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
				},{
					xtype : 'field_device_deviceatmtype',
					emptyText :  EwayLocale.combox.select
				}]
			}, {
				columnWidth : .44,
				defaults : {
					labelAlign : 'right',
					labelWidth : 90
				},
				items : [{
						xtype : 'field_atmType_DeviceAtmCatalogComboBox',
						fieldLabel : EwayLocale.machine.atmGroup.devCatalogName,
						labelWidth: 100
					},{
						xtype : 'combobox',
						fieldLabel : EwayLocale.machine.device.onBankSignal,
						emptyText :  EwayLocale.combox.select,
						labelWidth: 100,
						name : 'awayFlag',
						hiddenName : 'awayFlag',
						editable : false,
						store: 'machine.DeviceAwayFlagComboBox',
						valueField : 'value',
						displayField : 'display',
						queryMode : 'local'
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});