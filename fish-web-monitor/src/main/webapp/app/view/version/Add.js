
Ext.define('Eway.view.version.Add', {
	extend : 'Ext.window.Window',
	alias : 'widget.version_add',

	requires : ['Ext.form.field.File','Eway.view.version.field.Desc',
	            'Eway.lib.Util',
	            'Eway.view.version.field.VersionComboBox','Eway.view.version.field.VersionTypeComboBoxAdd'],

	title : '增加版本信息',
	modal : true,
	resizable : false,
	constrainHeader : true,
	width : 600,

	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 10px 10px',
				trackResetOnLoad : true,
				defaults : {
					labelWidth : 100,
					anchor: '100%',
					labelAlign : 'right',
					msgTarget : 'side',
					grow : true
				},
				items : [{
					xtype:'field_versionTypeComboBoxAdd',
					fieldLabel : '<font color="red">*</font>版本类型'
				},{
				   xtype: 'textfield',
			       fieldLabel : '<font color="red">*</font>版本号',
				   name : 'versionNo',
				   allowBlank: false,
				   maxLength: 40,
				   vtype:'versionNo'
				},{
			        xtype: 'fieldcontainer',
			        fieldLabel: '<font color="red">*</font>版本路径',
			        combineErrors: true,
			        hidden: true,
			        layout: 'hbox',
			        defaults: {
			            hideLabel: true
			        },
			        items: [{
								xtype: 'textfield',
								fieldLabel : '<font color="red">*</font>版本路径',
								name : 'versionPath',
								allowBlank: false,
								width: 230,
								regex:/^[a-zA-z]{1}:([/][\w-]+)*$/i,
								regexText:'不符合文件路径规则，规则如下：1.文件名只能包含英文字母(a-z A-Z)、数字(0-9)、下划线(_)、横线(-) ； 2.路径统一用正斜杠(/)作为分隔符 ；3.不区分大小 ; 示例 E: E:/yihua',
								maxLength:50
							},
							{
								xtype: 'displayfield',
								value: '(版本文件在自助设备上的安装路径)',
								margin: '0 0 0 5'
							}]
				},{
					xtype: 'filefield',
					fieldLabel:'<font color="red">*</font>版本文件',
					emptyText:'请将要下发的版本文件(或者文件夹)打包zip或rar格式',
					name: 'file',
					waitMsg:'正在上传文件...',
					allowBlank : false,
					buttonText: '浏览...',
					regex : /^([\w|\W]*)(\.zip|rar)$/i,
					regexText:'只能上传zip或rar格式的文件'
				},{
					name:'dependVersion',
					fieldLabel:'依赖版本',
					disabled :true,
					xtype: 'combobox',
					editable  : false,
					store: Ext.create('Ext.data.Store',{
						fields: ['id', 'displayName'],
						proxy: {
					        type: 'rest',
					        url : 'api/version/version/combo',
					        reader: {
					            type: 'json',
					            rootProperty : 'data'
					        }
					    },
					    pageSize:100,
					    autoLoad : false
					}),
					emptyText : '',
					mode : 'remote',
					triggerAction: 'all',
					valueField : 'id',
					displayField : 'displayName',
					listConfig: {
					        getInnerTpl: function() {
					            return '<div data-qtip="{displayName}">{displayName}</div>';
					        }
					}/*,
					pageSize: 20*/
				},{
					   xtype: 'textfield',
				       fieldLabel : '升级前执行脚本',
					   name : 'execBefore',
					   maxLength: 50,
					   emptyText:'请输入升级包中的以bat或cmd结尾的文件',
					   regex : /^([\w|\W]*)(\.bat|cmd)$/i,
					   regexText:'只能输入bat或cmd结尾的文件'
				},{
					xtype:'hidden',
					name:'dependVersionId'
				},{
					xtype : 'field.desc',
					maxLength : 100
				},{
		            xtype: 'fieldcontainer',
		            fieldLabel: '其他配置',
		            defaultType: 'checkboxfield',
		            items: [
		                {
		                    boxLabel  : '允许自动更新(当ATM向服务器检查新版本时，允许自动更新的版本才可以返回给ATM)',
		                    name      : 'autoDown',
		                    inputValue: 'true'
		                }/*, {
		                    boxLabel  : '重启ATM（通知应用重启）',
		                    name      : 'eagerRestart',
		                    checked : false,
		                    inputValue: 'true'
		                }*/, {
		                    boxLabel  : '自动解压缩(选中此项时，在ATM端会自动解压缩)&nbsp;<font color="red">注意：如果版本文件本来不符合zip格式，后被压缩成zip时，请选中此项！</font>',
		                    name      : 'uncompress',
		                    checked : false,
		                    inputValue: 'true'
		                }
		            ]
		        },{
					xtype:'hidden',
					hideLabel : true,
					fieldLabel: '文件在服务器上的位置',
					name:'serverPath'
				} ],
				buttonAlign : 'center',
				buttons : [ {
					text : '确认',
					action : 'confirm'
				}, {
					text : '取消',
					handler : this.onOver
				} ]
			}
		});
		this.callParent(arguments);
	},

	onReset : function() {
		this.up('form').getForm().reset();
	},

	onOver : function() {
		this.up('window').close();
	}
});