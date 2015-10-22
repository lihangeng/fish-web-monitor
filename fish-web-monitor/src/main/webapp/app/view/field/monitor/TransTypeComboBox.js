
Ext.define('Eway.view.field.monitor.TransTypeComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.monitor_TransTypeComboBox',
	fieldLabel : Eway.locale.monitor.business.transaction.transCode,
	name : 'transCode',
	hiddenName : 'transCode',
	msgTarget : 'side',
	store: 'monitor.transaction.TransType',
	valueField : 'transCode',
	displayField : 'codeDesc',
	queryMode : 'local',
	editable : false,
	emptyText: Eway.locale.combox.select,
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