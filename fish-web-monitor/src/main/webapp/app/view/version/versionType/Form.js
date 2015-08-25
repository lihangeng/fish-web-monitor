Ext.define('Eway.view.version.versionType.Form', {
	extend : 'Eway.view.base.Form',
	alias : 'widget.versionType_form',
	
	bodyStyle : 'padding: 10px 20px 10px 10px',
	defaults : {
		anchor : '100%',
		labelAlign : 'right',
		msgTarget : 'side',
		labelWidth:150
	},
	initComponent : function() {
		Ext.apply(this, {
				items: [{
					xtype: 'textfield',
					fieldLabel:'<font color="red">*</font>软件分类编码',
					allowBlank: false,
					name:'typeName',
					maxLength: 64,
					regex : /^[a-zA-Z0-9_-]{1,30}$/,
					regexText :'只能输入字母(a-z或A-Z)、数字(0-9)、下划线(_)、横线(-)'
				},{
					xtype: 'textfield',
					fieldLabel:'<font color="red">*</font>软件分类名称',
					allowBlank: false,
					name :'desc',
					maxLength: 128
				},{
					xtype:'textfield',
					fieldLabel: '<font color="red">*</font>默认安装路径',
					allowBlank: false,
					name : 'defaultInstallPath',
					maxLength: 50,
					regex:/^[a-zA-z]{1}:([/][\w-]+)*$/i,
					regexText:'不符合文件路径规则，规则如下：1.文件名只能包含英文字母(a-z A-Z)、数字(0-9)、下划线(_)、横线(-) ； 2.路径统一用正斜杠(/)作为分隔符 ；3.不区分大小 ; 示例 E: E:/yihua'
				},{
					xtype:'hidden',
					name : 'versionCatalog',
					value:'APP'
				},
				{
		            xtype: 'fieldcontainer',
		            fieldLabel: '需要重启设备完成升级',
		            defaultType: 'checkboxfield',
		            items: [
		                {
		                    boxLabel  : '',
		                    name      : 'autoDeploy',
		                    inputValue: 'true'
		                }
		            ]
		        },
		        {
		            xtype: 'checkboxgroup',
		            fieldLabel: '适用的设备型号',
		            columns: 3,
		            loader : {//使用自定义的加载方式
						autoLoad: false,
						url : 'api/version/versionType/atmType',
						params : {
							versionTypeId : 0//this.up('form').getForm().findField('id').getValue()
						},
						renderer:	function(loader, response, active){
							var success = true,
			                    target = loader.getTarget(),
			                    items = [];
			                try {
			                	var text = Ext.decode(response.responseText);
			                	if(Ext.isString(text.data)){
			                		items = Ext.decode(text.data);//解析从后台返回的菜单列表是字符串的情况
			                	}else{
			                		items = text.data;
			                	}
			                } catch (e) {
			                    success = false;
			                }

			                if (success) {
			                    if (active.removeAll) {
			                        target.removeAll();
			                    }
			                    target.add(items);
			                }
			                return success;
						}
					}
				}
				]
		});
		this.callParent(arguments);
	}
});