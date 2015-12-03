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
			title: EwayLocale.machine.device.addDevInfo,
			items : [ {
				columnWidth : .3,
				defaults : {
					labelAlign : 'right',
					labelWidth : 120
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : EwayLocale.machine.atmGroup.terminalId,
					name : 'terminalId',
					msgTarget : 'side'
				}, {
					xtype : 'field_atmType_DeviceAtmCatalogComboBox',
					fieldLabel : EwayLocale.machine.atmGroup.devCatalogName
				}]
			},{

				columnWidth : .36,
				defaults : {
					labelAlign : 'right',
					labelWidth : 140
				},
				items : [  {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organization'
				},  {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organizationID',
					value : ewayUser.getOrgId()
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
				} ]
			
			}, {
				columnWidth : .3,
				defaults : {
					labelWidth: 120,
					labelAlign : 'right'
				},
				items : [ {
					xtype : 'field_atmType_DeviceAtmVendorComboBox',
					fieldLabel : EwayLocale.machine.atmGroup.devVendorName
				},{
					xtype : 'field_device_deviceatmtype',
					name : 'devType',
					hiddenName : 'devType'
				}  ]
			} ]
		});
		this.callParent(arguments);
	}
});