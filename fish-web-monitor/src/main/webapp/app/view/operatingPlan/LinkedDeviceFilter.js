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
					labelWidth : 60
				},
				items : [ {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.refs.orgName,
					emptyText : EwayLocale.machine.serviceplan.placeSelect,
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
					regexText : EwayLocale.vtype.ip
				}]
			}, {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right',
					labelWidth : 60
				},
				items : [  {
					xtype : 'textfield',
					fieldLabel :EwayLocale.commen.terminalId,
					name : 'terminalId',
					regex : /^\w+[\w-_\.]*$/,
					regexText :EwayLocale.vtype.terminalId,
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