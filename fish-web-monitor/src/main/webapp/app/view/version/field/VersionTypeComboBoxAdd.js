Ext.define('Eway.view.version.field.VersionTypeComboBoxAdd', {
			extend : 'Ext.form.field.ComboBox',
			alias : 'widget.field_versionTypeComboBoxAdd',

			config:{
				selectFirst:false //是否选中第一条
			},
			fieldLabel : '版本类型',
			name : 'versionTypeId',
			editable : false,
			autoScroll : true,
			allowBlank: false,
			store : 'version.ComboVersionType',
			valueField : 'id',
			displayField : 'desc',
			queryMode : 'local',
			emptyText : '-请选择版本类型-',
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
				afterrender:function(){
					if(this.selectFirst){
						var me = this;
						var store = this.getStore();
						this.store.load({
							callback : function(records, operation, success) {
								if (success) {
									var record = store.getAt(0);
									if(undefined!=record){
										me.select( record );
									}
								}
							},
							scope : this
						});
					}
				}
			}
		});