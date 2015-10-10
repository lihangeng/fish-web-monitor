Ext.define('Eway.view.report.baseReport.DeviceTypeCountReportFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.baseReport_DeviceTypeCountReportFilter',

	requires : ['Eway.view.common.OrgComboOrgTree',
				'Eway.view.field.card.DeviceAtmVendorComboBox',
				'Eway.view.field.card.DeviceTypeComboBox',
				'Eway.view.field.person.OrganizationLevel'],
			
	height : 70,
	layout : 'column',
	hideLabel : false,
	initComponent : function() {
		var levelStore = Ext.create('Eway.store.person.organization.OrganizationLevelDict');
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
									xtype : 'field.organizationLevel',
									name : 'orgLevel',
									store : levelStore,
									emptyText : '--请选择--'
								}]
					},{
						columnWidth : .3,
						items : [{
							xtype : 'field_card_DeviceAtmVendorComboBox',
							labelAlign : 'right'
						}]
					
					},{
						columnWidth : .3,
						items : [{
									xtype : 'field_card_DeviceTypeComboBox',
									labelAlign : 'right'
						}]
					}]
		});

		this.callParent(arguments);
	}
});