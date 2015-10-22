Ext.define('Eway.view.field.atmType.DeviceAtmVendorForTypeComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_atmType_DeviceAtmVendorForTypeComboBox',

	fieldLabel : Eway.locale.machine.atmType.devVendorName,
	name : 'devVendorId',
	hiddenName : 'devVendorId',
	msgTarget : 'side',
	store : 'machine.atmType.DeviceAtmVendor',
	valueField : 'id',
	displayField : 'name',
	queryMode : 'local',
	editable : false,
	emptyText : Eway.locale.combox.select,
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
		}
	}
});