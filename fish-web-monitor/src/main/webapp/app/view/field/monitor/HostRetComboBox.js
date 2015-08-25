Ext.define('Eway.view.field.monitor.HostRetComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.monitor_HostRetComboBox',
	fieldLabel : '主机返回码',
	name : 'hostRet',
	hiddenName : 'hostRet',
	msgTarget : 'side',
	store : 'monitor.transaction.HostRet',
	valueField : 'hostRet',
	displayField : 'name',
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