Ext.define('Eway.view.version.field.VersionTypeComboBox', {
			extend : 'Ext.form.field.ComboBox',
			alias : 'widget.field_versionTypeComboBox',

			fieldLabel : '版本类型',
			name : 'versionTypeId',
			editable : false,
			store : Ext.create('Ext.data.Store', {
						fields : ['id', 'typeName', 'desc'],
						proxy : {
							type : 'rest',
							url : 'api/version/versionType/combo',
							reader : {
								type : 'json',
								rootProperty : 'data'
							}
						},
						autoLoad : false
					}),
			emptyText : '',
			mode : 'local',
			triggerAction : 'all',
			valueField : 'id',
			displayField : 'typeName',
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