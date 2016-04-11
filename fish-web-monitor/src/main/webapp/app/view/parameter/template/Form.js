Ext.define('Eway.view.parameter.template.Form', {

	extend : 'Eway.view.base.Form',
	alias : 'widget.parameter_template_form',

	requires : [],
	defaults : {
		labelWidth : 120,
		labelAlign : 'right',
		msgTarget : 'side',
		width : 400
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '90%',
				labelWidth : 120,
				labelAlign : 'right',
				msgTarget : 'side'
			},
			items : [ {
				fieldLabel : '<font color="red">*</font>模板名称',
				xtype : 'textfield',
				name : 'name',
				maxLength : 30,
				msgTarget : 'side',
				allowBlank : false
			}, {
				xtype : 'textarea',
				fieldLabel : '模板备注',
				name : 'remark',
				autoScroll : true,
				maxLength : 30,
				msgTarget : 'side',
				allowBlank : true
			} ]
		});
		this.callParent(arguments);
	}

});