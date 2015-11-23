Ext.define('Eway.view.version.deviceVersion.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.deviceVersion_filterForm',

	requires : [ 'Eway.view.common.OrgComboOrgTree','Eway.view.field.card.DeviceAtmVendorComboBox'
	             ,'Eway.view.field.card.DeviceTypeComboBox'],
	height : 80,
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .3,
				defaults : {
					labelAlign : 'right'
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : EwayLocale.refs.terminalId,
					name : 'terminalId'
				},{
					xtype : 'field_card_DeviceAtmVendorComboBox',
					name: 'devBrandId'
				}]
			}, {
				columnWidth : .3,
				defaults : {
					labelAlign : 'right'
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : EwayLocale.refs.ip,//'设备IP地址',
					name : 'ip',
					regex : /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,
					regexText : EwayLocale.vtype.ip//'请输入正确的IP地址'
				},{
					xtype : 'field_card_DeviceTypeComboBox',
					name:'devTypeId'
				}]
			},{
				columnWidth : .3,
				defaults : {
					labelAlign : 'right'
				},
				items : [{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organization'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.refs.orgName,//
					emptyText : EwayLocale.combox.select,
					name : 'orgName',
					hiddenValue : 'organization',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				}/*,{
					xtype : 'field_device_deviceatmtype',
					name : 'devType',
					hiddenName : 'devType'
				}*/]
			}]
		});
		this.callParent(arguments);
	}

});