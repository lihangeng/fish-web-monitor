Ext.define('Eway.view.report.baseReport.DeviceHardWareReportFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.baseReport_DeviceHardWareReportFilter',

	requires : ['Eway.view.common.OrgComboOrgTree'],
			
	height : 40,
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
									fieldLabel : '所属机构',
									labelAlign : 'right',
									emptyText : '--请选择--',
									name : 'orgName',
									hiddenValue : 'orgId',
									editable : false,
									filters : '{"type" : "0"}',
									rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
								}]
					},{
						columnWidth : .3,
						items : [{
									xtype : 'textfield',
									name : 'terminalId',
									fieldLabel : '设备号',
									labelAlign : 'right'
						}]
					
					}]
		});

		this.callParent(arguments);
	}
});