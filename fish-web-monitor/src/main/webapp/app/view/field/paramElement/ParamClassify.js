
Ext.define('Eway.view.field.paramElement.ParamClassify', {
	extend : 'Ext.form.field.ComboBox',
	alias: 'widget.field_paramElement_ParamClassify',

	fieldLabel : '参数分类',
	name : 'paramClassify',
	hiddenName : 'paramClassify',
	msgTarget : 'side',
	store : 'parameter.element.ElementClassify',
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
		}
	}

});