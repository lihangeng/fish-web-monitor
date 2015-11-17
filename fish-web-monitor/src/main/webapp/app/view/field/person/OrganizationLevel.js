
Ext.define('Eway.view.field.person.OrganizationLevel', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.organizationLevel',
	labelAlign : 'right',
	fieldLabel : EwayLocale.person.bankOrg.orgLevel,
	msgTarget : 'side',
	store : '',
	valueField : 'value',
	displayField : 'display',
	editable : false,
	queryMode : 'local',
	emptyText : EwayLocale.combox.select,
	clearClick: function(){
		this.setValue("");
	}

});

