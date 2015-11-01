Ext.define('Eway.view.operatingPlan.LinkedDeviceFilter',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.operatingPlan_linkedDeviceFilter',
	requires : [ 'Eway.lib.Util','Eway.model.version.SelectableDevice',
				 'Eway.view.field.device.DeviceAtmType','Eway.view.common.OrgComboOrgTree', 'Eway.view.field.atmType.DeviceAtmCatalogComboBox' ],

	layout : 'column',


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
					value : '0'
				},{
					xtype : 'textfield',
					fieldLabel : 'IP',
					name : 'ip',
					regex : /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,
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
					fieldLabel : '设备号',
					name : 'terminalId',
					regex : /^\w+[\w-_\.]*$/,
					regexText : '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’，只能以字母、数字或下划线开头。',
					maxLength : 20
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'planId'
				},{
					xtype : 'field_device_deviceatmtype',
					name : 'devType',
					hiddenName : 'devType'
				} ]
			}
			]
		});
		this.callParent(arguments);
	}

});