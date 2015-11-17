
Ext.define('Eway.view.field.param.ParamKey', {
	extend: 'Ext.form.field.Text',
	alias: 'widget.field_param_ParamKey',
	
	fieldLabel : EwayLocale.machine.param.paramKey,
	maxLength : 20,
	name : 'paramKey',
	msgTarget : 'side'
	
});