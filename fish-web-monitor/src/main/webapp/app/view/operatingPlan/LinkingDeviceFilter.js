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
					labelWidth : 70
				},
				items : [ {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '机构',
					emptyText : '--请选择--',
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
				    regexText : '请输入正确的IP地址'
				}]
			}, {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right',
					labelWidth : 80
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