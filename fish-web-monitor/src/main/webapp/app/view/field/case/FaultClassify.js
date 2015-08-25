Ext.define('Eway.view.field.case.FaultClassify', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_case_faultClassify',
	fieldLabel : '故障分类',
	name : 'faultClassify',
	hiddenName : 'faultClassify',
	store : 'case.FaultClassify',
	valueField : 'id',
	displayField : 'classifyName',
	queryMode : 'local',
	editable : false,
	emptyText : '--请选择--',
	listeners : {
		beforerender : function() {
			this.store.load();
		},
		change : function(text, newValue, oldValue) {
			if (newValue && newValue !== "") {
				text.getTrigger("clear").show();
			} else {
				text.getTrigger("clear").hide();
			}
		}
	}

});