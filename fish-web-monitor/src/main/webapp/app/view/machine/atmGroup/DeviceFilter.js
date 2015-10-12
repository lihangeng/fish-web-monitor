Ext.define('Eway.view.machine.atmGroup.DeviceFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.atmGroup_deviceFilter',
	requires : [ 'Eway.view.common.OrgComboOrgTree',
	             'Eway.view.field.device.DeviceAtmType',
	             'Eway.view.field.atmType.DeviceAtmVendorComboBox',
	             'Eway.lib.Util',
	             'Eway.view.field.atmType.DeviceAtmCatalogComboBox' ],
	height : 100,
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right',
					labelWidth : 70
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : Eway.locale.machine.atmGroup.terminalId,
					name : 'terminalId',
					msgTarget : 'side',
					maxLength : 20
				}, {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'devService'
				}, {
					xtype : 'field_atmType_DeviceAtmVendorComboBox',
					fieldLabel : Eway.locale.machine.atmGroup.devVendorName
				}, {
				xtype : 'common_orgComboOrgTree',
				fieldLabel : Eway.locale.machine.atmGroup.devServiceName,
				emptyText : Eway.locale.combox.select,
				name : 'devServiceName',
				hiddenValue : 'devService',
				editable : false,
				filters : '{"type" : "1"}',
				rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
				} ]
			}, {
				columnWidth : .5,
				defaults : {
					labelWidth: 70,
					labelAlign : 'right'
				},
				items : [ {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'devService'
				}, {
					xtype : 'field_device_deviceatmtype',
					name : 'devType',
					hiddenName : 'devType'
				}, {
					xtype : 'field_atmType_DeviceAtmCatalogComboBox',
					fieldLabel : Eway.locale.machine.atmGroup.devCatalogName
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
					fieldLabel : Eway.locale.machine.atmGroup.orgName,
					emptyText : Eway.locale.combox.select,
					name : 'orgName',
					hiddenValue : 'organization',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});