Ext.define('Eway.view.cash.boxInfo.FilterForm',{
	extend: 'Eway.view.base.FilterForm',
	alias : 'widget.boxInfo_filterForm',
	requires : [ 'Eway.view.common.OrgComboOrgTree', 'Eway.view.field.device.DeviceAtmType',
	             'Eway.view.field.atmType.DeviceAtmVendorComboBox',
	             'Eway.view.field.atmType.DeviceAtmCatalogComboBox',
	             'Eway.lib.Util'],
	closable:false,
	layout : 'column',
	height: 'auto',
	initComponent : function(){
		Ext.apply(this,{
			items: [ {
				columnWidth : .28,
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
					xtype : 'textfield',
					fieldLabel :  EwayLocale.machine.atmGroup.ip,
					name : 'ip',
					regex : /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,
					regexText : EwayLocale.vtype.ip,
					msgTarget : 'side'
				}]
			}, {
				columnWidth : .38,
				defaults : {
					labelAlign : 'right',
					labelWidth:160
				},
				items : [  {
					xtype : 'field_device_deviceatmtype',
					name:'devType',
					emptyText :  EwayLocale.combox.select
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
					rootVisible : Eway.user.getOrgType() != "" && Eway.user.getOrgType() == '0' ? true : false
				}]
			}, {
				columnWidth : .33,
				defaults : {
					labelAlign : 'right',
					labelWidth:160
				},
				items : [
				         {
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
				} ,  {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organizationID',
					value : Eway.user.getOrgId()
				}, {
					xtype : 'field_atmType_DeviceAtmVendorComboBox',
					fieldLabel : EwayLocale.machine.atmGroup.devVendorName
				}
					]
			} ]
		});
		this.callParent(arguments);
	}
	
});