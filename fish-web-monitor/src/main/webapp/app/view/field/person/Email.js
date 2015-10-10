
Ext.define('Eway.view.field.person.Email', {
	extend: 'Ext.form.field.Text',
	alias: 'widget.field.email',

	fieldLabel: Eway.locale.commen.email,
	name: 'email',
	vtype : 'email',
	maxLength : 50
});