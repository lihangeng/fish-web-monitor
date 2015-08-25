Ext.define('Eway.view.version.field.VersionComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_versionComboBox',

	fieldLabel : '下发的版本',
	name: 'versionId',
	editable  : false,
	store: Ext.create('Ext.data.Store',{
		fields: ['id', 'fullName','versionNo','versionType'],
		proxy: {
	        type: 'rest',
	        url : 'api/version/version/combo',
	        reader: {
	            type: 'json',
	            rootProperty : 'data'
	        }
	    },
	    autoLoad : false						
	}),
	emptyText : '请选择下发的版本',
	mode : 'remote',
	triggerAction: 'all',
	valueField : 'id',
	displayField : 'fullName',
	listConfig: {
	        getInnerTpl: function() {
	            return '<div data-qtip="{versionType}. {versionNo}">{versionType} ({versionNo})</div>';
	        }
	},
	pageSize: 20
});