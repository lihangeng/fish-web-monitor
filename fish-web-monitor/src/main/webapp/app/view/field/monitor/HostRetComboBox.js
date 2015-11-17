Ext.define('Eway.view.field.monitor.HostRetComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.monitor_HostRetComboBox',
	fieldLabel : EwayLocale.monitor.business.transaction.hostRet,
	name : 'hostRet',
	hiddenName : 'hostRet',
	msgTarget : 'side',
	store : 'monitor.transaction.HostRet',
	valueField : 'hostRet',
	displayField : 'name',
	queryMode : 'local',
	editable : false,
	emptyText : EwayLocale.combox.select,
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