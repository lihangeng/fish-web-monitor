Ext.define('Eway.view.report.baseReport.DeviceBoxDetailReportFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.baseReport_DeviceBoxDetailReportFilter',

	requires : ['Eway.view.common.OrgComboOrgTree',
				'Eway.view.field.card.DeviceAtmVendorComboBox',
				'Eway.view.field.card.DeviceTypeComboBox'],
			
	height : 80,
	layout : 'column',
	hideLabel : false,
	initComponent : function() {
		Ext.apply(this, {
			items : [{
						columnWidth : .3,
						items : [{
									style : 'padding-top:0px',
									xtype : 'hiddenfield',
									name : 'orgId'
								}, {
									//只带出银行机构
									xtype : 'common_orgComboOrgTree',
									fieldLabel : Eway.locale.commen.orgNameBelongs,
									labelAlign : 'right',
									emptyText : '--请选择--',
									name : 'orgName',
									hiddenValue : 'orgId',
									editable : false,
									filters : '{"type" : "0"}',
									rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
								},{
									xtype : 'textfield',
									name : 'terminalId',
									fieldLabel : Eway.locale.commen.terminalId,
									labelAlign : 'right'
								}]
					},{
						columnWidth : .3,
						items : [{
									xtype : 'field_card_DeviceAtmVendorComboBox',
									labelAlign : 'right'
						}/*,{
									xtype : 'combobox',
									name : 'setupId',
									fieldLabel : '安装类型',
									queryMode: 'local',
									store : 'monitor.report.DeviceSetupTypeComboBox',
									displayField: 'display',
									valueField: 'value',
									emptyText: '--请选择--'
						}*/]
					
					},{
						
						columnWidth : .3,
						items : [{
									xtype : 'field_card_DeviceTypeComboBox',
									labelAlign : 'right'
						}/*,{
									xtype : 'combobox',
									name : 'deviceStatusId',
									fieldLabel : '终端状态',
									queryMode: 'local',
									store : 'monitor.report.DeviceStatusComboBox',
									displayField: 'display',
									valueField: 'value',
									emptyText: '--请选择--'
						}*/]
					
					
					}]
		});

		this.callParent(arguments);
	}
});