
Ext.define('Eway.view.field.paramElement.ParamRights', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_paramElement_ParamRights',

	fieldLabel : '参数权限',
	name : 'paramRights',
	hiddenName : 'paramRights',
	msgTarget : 'side',
	store: 'parameter.element.ParamRights',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select
});


