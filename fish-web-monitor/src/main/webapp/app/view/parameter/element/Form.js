Ext.define('Eway.view.parameter.element.Form',{

	extend : 'Eway.view.base.Form',
	alias : 'widget.parameter_element_form',

	requires: ['Eway.view.field.paramElement.ParamName',
	           'Eway.view.field.paramElement.ParamValue',
	           'Eway.view.field.paramElement.ParamType',
	           'Eway.view.field.paramElement.VersionNo',
	           'Eway.view.field.paramElement.ParamClassify',
	           'Eway.view.field.paramElement.ParamRights',
	           'Eway.view.field.paramElement.ParamBelongs',
	           'Eway.view.field.paramElement.CreateTime',
	           'Eway.view.field.paramElement.LastModifyTime',
	           'Eway.view.field.paramElement.Remark'
	           ],
	defaults: {
					labelWidth: 120,
					labelAlign: 'right',
					msgTarget : 'side',
					width: 400
				},
	initComponent : function(){
		var rights=Ext.create('Eway.store.parameter.element.ParamRights');
		Ext.apply(this,{
			defaults: {
				anchor : '90%',
				labelWidth: 120,
				labelAlign: 'right',
				msgTarget : 'side'
			},
			items: [{
//					fieldLabel: '<font color="red">*</font> '+EwayLocale.machine.atmBrand.name,
					xtype : 'field_paramElement_ParamName',
					maxLength : 20,
					regex:/(^[a-zA-Z0-9_-\s]*$)/,
					regexText:'只能输入英文，数字，下划线，横线'
//					allowBlank: false
			},{
//				fieldLabel: '<font color="red">*</font> '+EwayLocale.machine.atmBrand.name,
				xtype : 'field_paramElement_ParamValue',
				maxLength : 20,
				allowBlank: false,
				regex:/(^[a-zA-Z0-9]*$)/,
				regexText:'只能输入字符和数字'
		    },{
//				fieldLabel: '<font color="red">*</font> '+EwayLocale.machine.atmBrand.name,
				xtype : 'field_paramElement_ParamType',
				maxLength : 20,
				allowBlank: false,

		   },{
//					fieldLabel: '<font color="red">*</font>'+ EwayLocale.machine.atmBrand.country,
					xtype : 'field_paramElement_VersionNo',
					maxLength : 30,
//					allowBlank: false
			},{
//					fieldLabel: '<font color="red">*</font>'+EwayLocale.machine.atmBrand.hotline1,
					xtype : 'field_paramElement_ParamClassify',
//					allowBlank: false,
//					regex:/(^[0-9]{3,4}[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/,
//					regexText: EwayLocale.tip.phone
			},{
					xtype : 'field_paramElement_ParamRights',
//					regex:/(^[0-9]{3,4}[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/,
//					regexText: EwayLocale.tip.phone
					editable : false,
					store:rights
			},{
					xtype : 'field_paramElement_ParamBelongs',
					editable : false,
					maxLength : 60
			},
			{
				xtype : 'field_paramElement_Remark',
				maxLength : 60
		    },{
				    xtype : 'field_paramElement_CreateTime',
				    maxLength : 60
		    },{
					xtype : 'field_paramElement_LastModifyTime',
					maxLength : 60
//					editable : false,
//					value: 1,
//					hidden:true
			}]
		});
		this.callParent(arguments);
	}

});