
Ext.define('Eway.view.field.paramElement.ParamBelongs', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_paramElement_ParamBelongs',

	fieldLabel : '<font color="red">*</font>'+EwayLocale.param.element.paramBelongs,
	name : 'paramBelongs',
	hiddenName : 'paramBelongs',
	msgTarget : 'side',
	store: 'parameter.element.ParamBelongs',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select

});