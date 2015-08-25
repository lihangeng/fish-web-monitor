
Ext.define('Eway.view.field.person.Email', {
	extend: 'Ext.form.field.Text',
	alias: 'widget.field.email',

	fieldLabel: '邮箱',
	name: 'email',
	vtype : 'email',
	maxLength : 50
});