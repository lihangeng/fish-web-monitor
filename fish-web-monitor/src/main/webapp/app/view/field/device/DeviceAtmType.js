Ext.define('Eway.view.field.device.DeviceAtmType', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_device_deviceatmtype',
	fieldLabel : '设备型号',
	name : 'devTypeId',
	hiddenName : 'devTypeId',
	store : 'machine.DeviceAtmType',
	valueField : 'id',
	displayField : 'name',
	queryMode : 'local',
	editable : false,
	emptyText : '--请选择--',
	listeners : {
		beforerender : function() {
//			this.proxy.extraParams = this.proxy.extraParams || {versionId:this.getVersionId()};
			this.store.load({
				callback : function(records, operation, success) {
					if (success) {
						// 给设备型号设置了值，但后台取型号列表还没有返回，而导致的此列为空
						// 所以当型号列表返回后，再进行一次验证。
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