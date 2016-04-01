
Ext.define('Eway.view.field.paramElement.ParamClassify', {
	extend : 'Ext.form.field.ComboBox',
	alias: 'widget.field_paramElement_ParamClassify',

	fieldLabel : '<font color="red">*</font>'+EwayLocale.param.element.paramClassify,
	name : 'classifyId',
	hiddenName : 'classifyId',
	msgTarget : 'side',
	store : 'parameter.element.ElementClassify',
	valueField : 'id',
	displayField : 'name',
	queryMode : 'local',
	editable : false,
	emptyText : EwayLocale.combox.select,
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