Ext.define('Eway.view.case.vendorCode.VendorCodeView',{
	extend : 'Eway.view.base.Panel',
	alias : 'widget.vendorCode_VendorCodeView',

	requires : ['Eway.view.case.vendorCode.VendorCodeFilterForm',
				'Eway.view.case.vendorCode.VendorCodeGrid'],

	title : Eway.locale.cases.vendorCode.providerFaultInfo,
	layout : 'border',

	isLoad : false,

	initComponent : function(){
		Ext.apply(this, {
			items : [{
				region : 'north',
				xtype : 'vendorCode_VendorCodeFilterForm'
			},{
				region : 'center',
				xtype : 'vendorCode_VendorCodeGrid'
			}],
			listeners : {
				activate : function(panel){
					if(!panel.isLoad){
						panel.isLoad = true;
						return;
					}
					panel.down('vendorCode_VendorCodeFilterForm').down('common_orgComboOrgTree').reflesh();
				}
			}
		});
		this.callParent(arguments);
	}
});