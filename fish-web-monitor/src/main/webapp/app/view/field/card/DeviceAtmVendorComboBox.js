Ext.define('Eway.view.field.card.DeviceAtmVendorComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_card_DeviceAtmVendorComboBox',

	fieldLabel : '所属品牌',
	name : 'devVendorId',
	hiddenName : 'devVendorId',
	msgTarget : 'side',
	store : 'monitor.card.DeviceAtmVendor',
	valueField : 'id',
	displayField : 'name',
	queryMode : 'local',
	emptyText : '--请选择--',
	listeners : {
		select : function() {
			var brand = this.up("form").query("field_card_DeviceTypeComboBox")[0];
			brand.store.load({
						url : 'api/machine/atmBrand/queryTypeByAtmVendor',
						params : {
							devVendorId : this.value
						}
					});
			brand.clearValue();
		},
		beforerender : function() {
			this.store.load();
		},
		// 判断此时当时去焦点时值为空，就加载全部的机型信息
		blur : function() {
//			var type = Ext.ComponentQuery
//					.query('field_card_DeviceTypeComboBox')[0];
			var type = this.up("form").query("field_card_DeviceTypeComboBox")[0];
			if (this.value == null) {
				type.store.load();
				type.clearValue();
			}
		}
	},
	triggerAction : 'all',
	anchor : '100%',
	remoteSort : true,
	editable : false,
	onClearClick : function() {
		var me = this;
		me.clearValue();
		var type = this.up("form").query("field_card_DeviceTypeComboBox")[0];
		type.store.load();
		type.clearValue();
	},
	clearClick : function() {
		this.setValue("");
	}

});