Ext.define('Eway.view.operatingPlan.LinkingDeviceFilter',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.operatingPlan_linkingDeviceFilter',
	requires : [ 'Eway.lib.Util','Eway.model.version.SelectableDevice',
				 'Eway.view.field.device.DeviceAtmType','Eway.view.common.OrgComboOrgTree', 'Eway.view.field.atmType.DeviceAtmCatalogComboBox' ],

	layout : 'column',
    name :'LinkingDeviceFilter',
	initComponent : function(){
		Ext.apply(this,{
			items : [ {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right',
					labelWidth : 60
				},
				items : [ {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : Eway.locale.refs.orgName,
					emptyText : Eway.locale.combox.select,
					name : 'orgName',
					hiddenValue : 'orgId',
					editable : true,
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
					value : '1'
				},  {
					xtype : 'textfield',
					fieldLabel : 'IP',
					name : 'ip',
				    regex:/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/,
				    regexText : Eway.locale.vtype.ip
				}]
			}, {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right',
					labelWidth : 60
				},
				items : [  {
					xtype : 'textfield',
					fieldLabel :Eway.locale.commen.terminalId,
					name : 'terminalId',
					regex : /^\w+[\w-_\.]*$/,
					regexText : Eway.locale.vtype.numberRule,
					maxLength : 20
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'planId'
				},{
					xtype : 'field_device_deviceatmtype',
					name : 'devType',
					hiddenName : 'devType'
				},{
					xtype: 'hidden',
					id: 'deviceIds',
					value:""
			}]
			}
			]
		});
		this.callParent(arguments);
	}

});