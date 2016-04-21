
Ext.define('Eway.view.parameter.element.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.element_FilterForm',

	requires:['Eway.view.field.paramElement.ParamBelongsRadioGroup'],

	layout : 'column',
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				columnWidth : .25,
				items : [{
					xtype : 'textfield',
					labelAlign : 'right',
					name : 'paramName',
					fieldLabel : EwayLocale.param.element.paramName,
					msgTarget : 'side'
				}]
			},{
				columnWidth : .25,
				items : [{
					xtype : 'field_paramElement_ParamClassify',
					labelWidth: 110,
					name : 'classifyId',
					fieldLabel :EwayLocale.param.element.paramClassify,
					editable : false,
					msgTarget : 'side',
					labelAlign : 'right'
				}]
			},{
				columnWidth : .25,
				items : [{
					xtype : 'field_paramElement_ParamBelongsRadioGroup',
					labelWidth: 110,
//					value: 1,
					editable : false,
					name : 'paramBelongsId',
					fieldLabel : EwayLocale.param.element.paramBelongs,
					msgTarget : 'side',
					labelAlign : 'right',
					checked:true
				}]
			},]
		});

		this.callParent(arguments);
	}
});