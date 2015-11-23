Ext.define('Eway.view.version.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.version_update',

	requires : ['Eway.view.version.field.Desc',
	            'Eway.view.version.field.VersionComboBox'],
	title: EwayLocale.version.UpdateTitle,//'更改版本信息'
	modal: true,
	resizable: false,
	constrainHeader: true,
	width: 600,

	initComponent: function() {
		var comboVersioncStore = Ext.create('Ext.data.Store',{
			idProperty: 'id',
			fields: ['id', 'displayName'],
			proxy: {
		        type: 'rest',
		        url : 'api/version/version/combo',
		        reader: {
		            type: 'json',
		            rootProperty : 'data'
		        }
		    },
		    pageSize : 500
		});
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				selectOnFocus : true,
				defaults: {
					anchor: '100%',
					labelWidth: 100,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
						xtype:'combobox',
						fieldLabel : '<font color="red">*</font>'+ EwayLocale.version.View.versionType,//版本类型',
						name: 'versionTypeId',
						emptyText :  EwayLocale.version.View.versionTypeEmpty,//'请选择版本类型',
						mode : 'remote',
						triggerAction: 'all',
						valueField : 'id',
						displayField : 'typeName',
						store: Ext.create('Ext.data.Store',{
								fields: ['id', 'typeName','desc'],
								proxy: {
							        type: 'rest',
							        url : 'api/version/versionType/combo',
							        reader: {
							            type: 'json',
							            rootProperty : 'data'
							        }
							    }
							}),
						disabled : true
				},{
				   xtype: 'textfield',
			       fieldLabel : '<font color="red">*</font>'+EwayLocale.version.View.versionNo,//版本号',
				   name : 'versionNo',
				   allowBlank: false,
				   maxLength: 40,
				   disabled: true
				}, {
					xtype:'combobox',
					fieldLabel : EwayLocale.version.View.dependVersion,//'依赖版本',
					name: 'dependVersion',
					editable  : false,
					store: comboVersioncStore,
					emptyText : EwayLocale.version.View.dependVersionEmptyText,//'请选择依赖类型',
					mode : 'remote',
					triggerAction: 'all',
					valueField : 'id',
					displayField : 'displayName',
					listConfig: {
					        getInnerTpl: function() {
					            return '<div data-qtip="{displayName}">{displayName}</div>';
					        }
					},
					minListWidth: 300/*,
					pageSize : 15*/
				},{
					   xtype: 'textfield',
				       fieldLabel : EwayLocale.version.View.execBefore,//'升级前执行脚本',
					   name : 'execBefore',
					   maxLength: 50,
					   emptyText: EwayLocale.version.View.execBeforeEmptyText,//'请输入升级包中的以bat或cmd结尾的文件',
					   regex : /^([\w|\W]*)(\.bat|cmd)$/i,
					   regexText:EwayLocale.version.View.execBeforeRegexText//'只能输入bat或cmd结尾的文件'
				},{
					xtype:'hidden',
					name:'dependVersionId'
				}, {
					xtype: 'field.desc',
					maxLength : 100
				},{
		            xtype: 'fieldcontainer',
		            fieldLabel: EwayLocale.version.View.otherConfigTitle,//'其他配置',
		            defaultType: 'checkboxfield',
		            items: [
		                {
		                    boxLabel  : EwayLocale.version.View.otherConfigAutoDown,//'允许自动更新(当ATM向服务器检查新版本时，允许自动更新的版本才可以返回给ATM)',
		                    name      : 'autoDown',
		                    inputValue: 'true'
		                }/*, {
		                    boxLabel  : '重启ATM（通知应用重启）',
		                    name      : 'eagerRestart',
		                    checked : false,
		                    inputValue: 'true'
		                }*/, {
		                    boxLabel  :  EwayLocale.version.View.otherConfigUncompress,//'自动解压缩(选中此项时，在ATM端会自动解压缩)&nbsp;<font color="red">注意：如果版本文件本来不符合zip格式，后被压缩成zip时，请选中此项！</font>',
		                    name      : 'uncompress',
		                    checked : false,
		                    inputValue: 'true'
		                }
		            ]
		        }],
		        buttonAlign : 'center',
				fbar: [{
					text: EwayLocale.button.confirm,//'确认',
				//	iconCls:'db-save',
					action:'confirm'
				},/* {
					text: '重置',
					handler: this.onReset
				},*/ {
					text: EwayLocale.button.cancle,//'取消',
				//	iconCls :'returnBtn',
					handler: this.onOver
				}]
			}
		});

		this.callParent(arguments);
	},

	onReset: function() {
		this.up('form').getForm().reset();
	},

	onOver: function() {
		this.up('window').close();
	}
});