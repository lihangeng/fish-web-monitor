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
				anchor : '100%',
				labelWidth: 120,
				labelAlign: 'right',
				msgTarget : 'side'
			},
			items: [{
					fieldLabel: '<font color="red">*</font> 品牌名称',
					xtype : 'field_atmBrand_name',
					maxLength : 20,
					maxLengthText : '允许的最大长度为20',
					allowBlank: false
			},{
					fieldLabel: '<font color="red">*</font> 生产商国家或地区',
					xtype : 'field_atmBrand_country',
					maxLength : 30,
					maxLengthText : '允许的最大长度为30',
					allowBlank: false
			},{
					fieldLabel: '<font color="red">*</font> 生产商热线1',
					xtype : 'field_atmBrand_hotline1',
					allowBlank: false,
					regex:/(^[0-9]{3,4}[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/,
					regexText: '请输入正确的电话号码'
			},{
					xtype : 'field_atmBrand_hotline2',
					regex:/(^[0-9]{3,4}[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/,
					regexText: '请输入正确的电话号码'
			},{
					xtype : 'field_atmBrand_address',
					maxLength : 60,
					maxLengthText : '允许的最大长度为60'
			},{
					fieldLabel: '生产商状态',
					xtype : 'field_atmBrand_statusComboBox',
					editable : false,
					value: 1,
					allowBlank: false
			}]
		});
		this.callParent(arguments);
	}
	
});