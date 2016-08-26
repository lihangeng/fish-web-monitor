Ext.define('Eway.view.cash.initPlan.FilterForm',{
	extend: 'Eway.view.base.FilterForm',
	alias : 'widget.initPlan_filterForm',
	requires : [ 'Eway.view.common.OrgComboOrgTree', 'Eway.view.field.device.DeviceAtmType',
	             'Eway.lib.Util'],
	closable:false,
	layout : 'column',
	height: 'auto',
	initComponent : function(){
		Ext.apply(this,{
			items: [ {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right'
				},
				items : [ {
				        xtype: 'datefield',
				        anchor: '100%',
				        fieldLabel: EwayLocale.initPlan.initDate,
				        name: 'date',
				        format:'Ymd',
				        value: new Date()  // defaults to today
				}]
			}, {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right',
					labelWidth:160
				},
				items : [{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organizationID',
					value : Eway.user.getOrgId()
				}, {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'orgId'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.machine.atmGroup.orgName,
					emptyText : EwayLocale.combox.select,
					name : 'orgName',
					hiddenValue : 'orgId',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : Eway.user.getOrgType() != "" && Eway.user.getOrgType() == '0' ? true : false
				}]
			} ]
		});
		this.callParent(arguments);
	}
	
});