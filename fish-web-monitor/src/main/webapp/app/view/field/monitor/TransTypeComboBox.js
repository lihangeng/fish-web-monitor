
Ext.define('Eway.view.field.monitor.TransTypeComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.monitor_TransTypeComboBox',
	fieldLabel : '交易类型',
	name : 'transCode',
	hiddenName : 'transCode',
	msgTarget : 'side',
	store: 'monitor.transaction.TransType',
	valueField : 'transCode',
	displayField : 'codeDesc',
	queryMode : 'local',
	editable : false,
	emptyText: '--请选择--',
	listeners: {
		beforerender: function(){
			this.store.load();
		},
		change:function(text,newValue,oldValue){
			if(newValue && newValue!== "" ){
				text.getTrigger("clear").show();
			}else{
				text.getTrigger("clear").hide();
			}
		}
	}
	
});