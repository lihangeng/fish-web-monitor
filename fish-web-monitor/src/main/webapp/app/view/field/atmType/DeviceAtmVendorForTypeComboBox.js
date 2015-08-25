Ext.define('Eway.view.field.atmType.DeviceAtmVendorForTypeComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_atmType_DeviceAtmVendorForTypeComboBox',

	fieldLabel : '所属品牌',
	name : 'devVendorId',
	hiddenName : 'devVendorId',
	msgTarget : 'side',
	store : 'machine.atmType.DeviceAtmVendor',
	valueField : 'id',
	displayField : 'name',
	queryMode : 'local',
	editable : false,
	emptyText : '--请选择--',
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