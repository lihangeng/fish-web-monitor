
Ext.define('Eway.view.field.param.ParamValue', {
	extend: 'Ext.form.field.Text',
	alias: 'widget.field_param_ParamValue',
	
	fieldLabel : EwayLocale.machine.param.paramValue,
	maxLength : 40,
	name : 'paramValue',
	msgTarget : 'side'
	
});