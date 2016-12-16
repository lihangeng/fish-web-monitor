Ext.define('Eway.view.field.device.DeviceAtmTypeToVersion', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_device_DeviceAtmTypeToVersion',
	fieldLabel : EwayLocale.machine.atmType.title,
	name : 'devTypeId',
	hiddenName : 'devTypeId',
	store : 'machine.DeviceAtmTypeToVersion',
	valueField : 'id',
	displayField : 'name',
	queryMode : 'local',
	editable : false,
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