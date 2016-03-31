Ext.define('Eway.view.parameter.devParameter.field.ParamBelongs', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.devParameter_field_ParamBelongs',

	name : 'paramBelongs',
	hiddenName : 'paramBelongs',
	msgTarget : 'side',
	store: 'parameter.devParameter.ParamBelongs',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select

});