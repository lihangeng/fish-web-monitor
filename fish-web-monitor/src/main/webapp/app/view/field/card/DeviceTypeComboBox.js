Ext.define('Eway.view.field.card.DeviceTypeComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_card_DeviceTypeComboBox',
	
	fieldLabel : EwayLocale.machine.atmType.devTerminalName,
	name : 'devTypeId',
	hiddenName : 'devTypeId',
	msgTarget : 'side',
	store: 'monitor.card.DeviceType',
	valueField : 'id',
	displayField : 'name',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select,
	triggerAction:'all',
	anchor:'100%',
	remote:true,
	editable : false,	
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
		}
	}
	
	

	
});