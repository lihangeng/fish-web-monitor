Ext.define('Eway.view.parameter.template.LinkedDeviceFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.parameter_linkedDeviceFilter',
	requires : [ 'Eway.lib.Util','Eway.model.version.SelectableDevice',
				 'Eway.view.field.device.DeviceAtmType','Eway.view.common.OrgComboOrgTree', 'Eway.view.field.atmType.DeviceAtmCatalogComboBox' ],

	layout : 'column',


	initComponent : function(){
		Ext.apply(this,{
			items : [ {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right',
					labelWidth : 40
				},
				items : [ {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.refs.orgName,
					emptyText : EwayLocale.combox.select,
					name : 'orgName',
					hiddenValue : 'orgId',
					editable : true,
					width:200,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				},  {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'orgId'
				},  {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'flag',
					value : '0'
				},{
					xtype : 'textfield',
					fieldLabel : 'IP',
					name : 'ip',
					width:200,
					regex : /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,
					regexText : EwayLocale.vtype.ip,
					msgTarget : 'side'
				}]
			}, {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right',
					labelWidth : 80
				},
				items : [  {
					xtype : 'textfield',
					fieldLabel :EwayLocale.commen.terminalId,
					width:240,
					name : 'terminalId'
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'planId'
				},{
					xtype : 'field_device_deviceatmtype',
					name : 'devType',
					width:240,
					hiddenName : 'devType'
				} ]
			}
			]
		});
		this.callParent(arguments);
	}

});