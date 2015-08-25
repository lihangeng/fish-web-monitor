Ext.define('Eway.view.field.atmType.DeviceAtmVendorComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_atmType_DeviceAtmVendorComboBox',

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
		select : function() {
			var type = this.up("form").query("field_device_deviceatmtype")[0];
			type.store.load({
				url : 'api/machine/atmBrand/queryTypeByAtmVendor',
				params : {
					devVendorId : this.value
				}
			});
			type.clearValue();
		},
		// 判断此时当时去焦点时值为空，就加载全部的机型信息
		blur : function() {
			if (this.value != null) {
				return;
			}
			var type = this.up("form").query("field_device_deviceatmtype")[0];
			type.store.load();
			type.clearValue();
		},
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
	},
	onClearClick : function() {
		var me = this;
		me.clearValue();
		var type = this.up("form").query("field_device_deviceatmtype")[0];
		type.store.load();
		type.clearValue();
	}
});