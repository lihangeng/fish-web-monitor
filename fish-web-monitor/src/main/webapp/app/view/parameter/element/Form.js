Ext.define('Eway.view.parameter.element.Form',{

	extend : 'Eway.view.base.Form',
	alias : 'widget.parameter_element_form',

	requires: ['Eway.view.field.paramElement.ParamName',
	           'Eway.view.field.paramElement.ParamValue',
	           'Eway.view.field.paramElement.ParamType',
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
					xtype : 'field_paramElement_ParamName',
					maxLength : 40,
					regex:/(^[a-zA-Z0-9_-\s]*$)/,
					allowBlank: false,
					regexText:EwayLocale.param.element.regex2
			},{
				xtype : 'field_paramElement_ParamValue',
				maxLength : 40,
				allowBlank: false,
		    },{
				xtype : 'field_paramElement_ParamType',
				maxLength : 10,
				allowBlank: false,
				editable:false
		   },{
					xtype : 'field_paramElement_ParamClassify',
					allowBlank: false,
			},{
					xtype : 'field_paramElement_ParamRights',
					editable : false,
					allowBlank: false,
					store:rights,
					maxLength : 10
			},{
					xtype : 'field_paramElement_ParamBelongs',
					editable : false,
					allowBlank: false,
					maxLength : 40,
					value:1
			},
			{
				xtype : 'field_paramElement_Remark',
				maxLength : 60
		    }]
		});
		this.callParent(arguments);
	}

});