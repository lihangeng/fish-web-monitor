
Ext.define('Eway.view.field.person.OrganizationLevel', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.organizationLevel',
	labelAlign : 'right',
	fieldLabel : '机构级别',
	msgTarget : 'side',
	store : '',
	valueField : 'value',
	displayField : 'display',
	editable : false,
	queryMode : 'local',
	emptyText : '--请选择--',
	clearClick: function(){
		this.setValue("");
	}

});

