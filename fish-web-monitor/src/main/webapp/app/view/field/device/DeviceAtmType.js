Ext.define('Eway.view.field.device.DeviceAtmType', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_device_deviceatmtype',
	fieldLabel : Eway.locale.machine.atmType.typeName,
	name : 'devTypeId',
	hiddenName : 'devTypeId',
	store : 'machine.DeviceAtmType',
	valueField : 'id',
	displayField : 'name',
	queryMode : 'local',
	editable : false,
	emptyText : Eway.locale.combox.select,
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