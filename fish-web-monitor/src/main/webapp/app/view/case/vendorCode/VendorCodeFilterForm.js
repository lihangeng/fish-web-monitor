Ext.define('Eway.view.case.vendorCode.VendorCodeFilterForm', {
	extend : 'Eway.view.base.Form',
	alias : 'widget.vendorCode_VendorCodeFilterForm',
	requires : [ 'Eway.view.common.OrgComboOrgTree','Ext.form.field.VTypes'],
	layout : 'column',
	hideLabel : false,
	hideLabel : false,
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .4,
				items : [{
						style : 'padding-top:0px',
						xtype : 'hiddenfield',
						name : 'vendor'
					  },
                      {
	                    xtype : 'common_orgComboOrgTree',
	                    labelAlign : 'right',
	                    fieldLabel : '厂商',
	                    emptyText : '--请选择--',
	                    name : 'name',
	                    hiddenValue : 'vendor',
	                    editable : false,
	                    filters : '{"type" : "1"}',
	                    rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
                      }]
			        },
			{
				columnWidth : .4,
				items : [ {
					xtype : 'textfield',
					labelAlign : 'right',
					name : 'code',
					fieldLabel : '厂商故障码',
					msgTarget : 'side'
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});