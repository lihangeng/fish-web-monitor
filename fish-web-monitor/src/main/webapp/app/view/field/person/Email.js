
Ext.define('Eway.view.field.person.Email', {
	extend: 'Ext.form.field.Text',
	alias: 'widget.field.email',

	fieldLabel: EwayLocale.commen.email,
	name: 'email',
	vtype : 'email',
	maxLength : 50
});