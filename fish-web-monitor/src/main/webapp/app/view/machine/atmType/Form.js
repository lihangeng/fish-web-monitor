Ext.define('Eway.view.machine.atmType.Form',{

	extend : 'Eway.view.base.Form',
	alias : 'widget.machine_atmType_form',

	requires: ['Eway.view.field.atmType.Dev_catalog',
	           'Eway.view.field.atmType.Dev_vendor',
	           'Eway.view.field.atmType.Name',
	           'Eway.view.field.atmType.No',
	           'Eway.view.field.atmType.Spec',
	           'Eway.view.field.atmType.Watt',
	           'Eway.view.field.atmType.Weight',
	           'Eway.view.field.atmType.CashtypeComboBox',
	           'Eway.view.field.atmType.DeviceAtmVendorComboBox',
	           'Eway.view.field.atmType.DeviceAtmCatalogComboBox',
	           'Eway.view.field.atmType.DeviceAtmVendorForTypeComboBox'],

	 initComponent : function(){
	 	Ext.apply(this,{
	 		defaults: {
				anchor : '100%',
				labelWidth: 120,
				labelAlign: 'right',
				msgTarget : 'side'
			},
	 		items: [/*{
	 			fieldLabel: '<font color="red">*</font> 编号',
				xtype : 'field_atmType_no',
				regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,20}$/,
				regexText: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以字母或数字开头。(1-20位)',
				allowBlank : false
			},*/{
				fieldLabel: '<font color="red">*</font> '+Eway.locale.machine.atmType.name,
				xtype : 'field_atmType_name',
				maxLength : 20,
				maxLengthText : Eway.locale.vtype.maxLength20,
				msgTarget : 'side',
				allowBlank : false
			},{
				fieldLabel: '<font color="red">*</font> '+Eway.locale.machine.atmType.devVendorName,
				xtype : 'field_atmType_DeviceAtmVendorForTypeComboBox',
				value : 1,
				allowBlank : false,
				editable : false
			},{
				fieldLabel: '<font color="red">*</font> '+Eway.locale.machine.atmType.devCatalogName,
				xtype : 'field_atmType_DeviceAtmCatalogComboBox',
				value : 2,
				allowBlank : false,
				editable : false
			},{
				xtype : 'field_atmType_spec',
				maxLength : 20,
				maxLengthText : Eway.locale.vtype.maxLength20
			},{
				xtype : 'field_atmType_weight',
				maxLength : 20,
				maxLengthText : Eway.locale.vtype.maxLength20
			},{
				xtype : 'field_atmType_watt',
				maxLength : 20,
				maxLengthText : Eway.locale.vtype.maxLength20
			},{
				fieldLabel: Eway.locale.machine.atmType.cashtype,
				xtype : 'field_atmType_cashtypeComboBox',
				value: '1',
				allowBlank : false,
				editable : false
			}]
	 	});
	 	this.callParent(arguments);
	 }
});