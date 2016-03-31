
Ext.define('Eway.view.field.paramElement.ParamType', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_paramElement_ParamType',

	fieldLabel : '参数值类型',
	name : 'paramType',
    hiddenName : 'paramType',
	msgTarget : 'side',
	value:2,
	store: 'parameter.element.ParamType',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select
});