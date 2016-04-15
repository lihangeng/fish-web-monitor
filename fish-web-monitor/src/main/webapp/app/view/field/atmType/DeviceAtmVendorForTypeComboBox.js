Ext.define('Eway.view.field.atmType.DeviceAtmVendorForTypeComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_atmType_DeviceAtmVendorForTypeComboBox',

	fieldLabel : EwayLocale.machine.atmType.devVendorName,
	name : 'devVendorId',
	hiddenName : 'devVendorId',
	msgTarget : 'side',
	store : 'machine.atmType.DeviceAtmVendor',
	valueField : 'id',
	displayField : 'name',
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