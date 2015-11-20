Ext.define('Eway.view.machine.atmBrand.Form',{

	extend : 'Eway.view.base.Form',
	alias : 'widget.machine_atmBrand_form',
	
	requires: ['Eway.view.field.atmBrand.Address',
	           'Eway.view.field.atmBrand.Country',
	           'Eway.view.field.atmBrand.Hotline1',
	           'Eway.view.field.atmBrand.Hotline2',
	           'Eway.view.field.atmBrand.Name',
	           'Eway.view.field.atmBrand.No',
	           'Eway.view.field.atmBrand.StatusComboBox'],
	defaults: {
					labelWidth: 120,
					labelAlign: 'right',
					msgTarget : 'side',
					width: 400
				},
	initComponent : function(){
		Ext.apply(this,{
			defaults: {
				anchor : '90%',
				labelWidth: 120,
				labelAlign: 'right',
				msgTarget : 'side'
			},
			items: [{
					fieldLabel: '<font color="red">*</font> '+EwayLocale.machine.atmBrand.name,
					xtype : 'field_atmBrand_name',
					maxLength : 20,
					allowBlank: false
			},{
					fieldLabel: '<font color="red">*</font>'+ EwayLocale.machine.atmBrand.country,
					xtype : 'field_atmBrand_country',
					maxLength : 30,
					allowBlank: false
			},{
					fieldLabel: '<font color="red">*</font>'+EwayLocale.machine.atmBrand.hotline1,
					xtype : 'field_atmBrand_hotline1',
					allowBlank: false,
					regex:/(^[0-9]{3,4}[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/,
					regexText: EwayLocale.tip.phone
			},{
					xtype : 'field_atmBrand_hotline2',
					regex:/(^[0-9]{3,4}[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/,
					regexText: EwayLocale.tip.phone
			},{
					xtype : 'field_atmBrand_address',
					maxLength : 60,
			},{
					xtype : 'field_atmBrand_statusComboBox',
					editable : false,
					value: 1,
					hidden:true
			}]
		});
		this.callParent(arguments);
	}
	
});