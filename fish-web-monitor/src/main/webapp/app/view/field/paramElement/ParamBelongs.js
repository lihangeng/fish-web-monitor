
Ext.define('Eway.view.field.paramElement.ParamBelongs', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_paramElement_ParamBelongs',

	fieldLabel : '<font color="red">*</font>'+EwayLocale.param.element.paramBelongs,
	name : 'paramBelongsId',
	hiddenName : 'paramBelongsId',
	msgTarget : 'side',
	store: 'parameter.element.ParamBelongs',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select,

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