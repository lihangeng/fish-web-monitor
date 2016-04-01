
Ext.define('Eway.view.field.paramElement.ParamRights', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_paramElement_ParamRights',

	fieldLabel : '<font color="red">*</font>'+EwayLocale.param.element.paramRights,
	name : 'paramRights',
	hiddenName : 'paramRights',
	msgTarget : 'side',
	store: 'parameter.element.ParamRights',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select
});


