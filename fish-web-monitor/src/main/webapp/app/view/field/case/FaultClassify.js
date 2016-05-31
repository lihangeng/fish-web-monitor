Ext.define('Eway.view.field.case.FaultClassify', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_case_faultClassify',
	fieldLabel : EwayLocale.cases.caseFault.faultClassify,
	name : 'faultClassify',
	hiddenName : 'faultClassify',
	store : 'case.FaultClassify',
	valueField : 'id',
	displayField : 'classifyName',
	queryMode : 'local',
	editable : false,
	emptyText : EwayLocale.combox.select,
	listeners : {
		beforerender : function() {
			this.store.load();
		},
		change:function(text,newValue,oldValue){
			if(newValue && newValue!== "" ){
				text.getTrigger("clear").show();
			}else{
				text.getTrigger("clear").hide();
			}
		},
		afterrender: function(text){
			var clearTip = text.getTrigger("clear");
			if(undefined==clearTip){
				return;
			}else{
				clearTip.hide();
			}
		}
	}

});