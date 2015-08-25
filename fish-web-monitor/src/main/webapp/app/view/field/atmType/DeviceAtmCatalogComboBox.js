
Ext.define('Eway.view.field.atmType.DeviceAtmCatalogComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_atmType_DeviceAtmCatalogComboBox',
	
	fieldLabel : '所属类型',
	name : 'devCatalogId',
	hiddenName : 'devCatalogId',
	msgTarget : 'side',
	store: 'machine.atmType.DeviceAtmCatalog',
	valueField : 'id',
	displayField : 'name',
	queryMode : 'local',
	editable : false,
	emptyText: '--请选择--',
	listeners: {
		beforerender: function(){
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