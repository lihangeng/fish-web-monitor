Ext.define('Eway.view.field.card.DeviceTypeComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_card_DeviceTypeComboBox',
	
	fieldLabel : Eway.locale.machine.atmType.devTerminalName,
	name : 'devTypeId',
	hiddenName : 'devTypeId',
	msgTarget : 'side',
	store: 'monitor.card.DeviceType',
	valueField : 'id',
	displayField : 'name',
	queryMode : 'local',
	emptyText: Eway.locale.combox.select,
	triggerAction:'all',
	anchor:'100%',
	remote:true,
	editable : false,
	onClearClick : function() {
		var me = this;
		me.clearValue();
	},
	clearClick: function(){
		this.setValue("");
	},
	listeners : {
		beforerender : function() {
			this.store.load();
		}
	}
	
});