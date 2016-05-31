Ext.define('Eway.view.field.card.DeviceAtmVendorComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_card_DeviceAtmVendorComboBox',

	fieldLabel : EwayLocale.machine.atmType.devVendorName,
	name : 'devVendorId',
	hiddenName : 'devVendorId',
	msgTarget : 'side',
	store : 'monitor.card.DeviceAtmVendor',
	valueField : 'id',
	displayField : 'name',
	queryMode : 'local',
	emptyText : EwayLocale.combox.select,
	listeners : {
		beforerender : function() {
			this.store.load({
				callback : function(records, operation, success) {
					if (success) {
						if (this.getValue()) {
							this.isValid();
						}
					}
				},
				scope : this
			});
		},
		change:function(text,newValue,oldValue){
			if(newValue && newValue!== "" ){
				text.getTrigger("clear").show();
			}else{
				text.getTrigger("clear").hide();
			}
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
