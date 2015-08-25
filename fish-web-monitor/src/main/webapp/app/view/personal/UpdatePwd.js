
Ext.define('Eway.view.personal.UpdatePwd', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.updatePwd',

	requires: ['Eway.view.field.person.Code','Eway.view.field.person.UserName','Eway.view.personal.UpdatePwdController'],

	title: '修改密码',
	
	controller : 'updatePwd',

	initComponent: function() {

		Ext.apply(Ext.form.field.VTypes,{
		    password:function(val,field){//val指这里的文本框值，field指这个文本框组件，大家要明白这个意思
		       if(field.confirmTo){//confirmTo是我们自定义的配置参数，一般用来保存另外的组件的id值
		           var pwd=Ext.getCmp(field.confirmTo);//取得confirmTo的那个id的值
		           return (val==pwd.getValue());
		       }
		       return true;
		    }
		});

		Ext.apply(this, {
			items : [{
				layout: {
			        type: 'vbox',
			        align:'center'
				},
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 10px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 350,
					labelWidth: 150,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					fieldLabel : '当前登录账号',
					xtype : 'field.code',
					allowBlank : false,
					readOnly: true,
					value:ewayUser.getCode()
				},{
					xtype : 'field.username',
					allowBlank : false,
					readOnly: true,
					value:ewayUser.getName()
				},{
				    xtype : 'textfield',
				    fieldLabel: '<font color="red">*</font> 输入原始密码',
				    name : 'password',
				    inputType : 'password',
				    allowBlank : false
				},{
					xtype : 'textfield',
					fieldLabel: '<font color="red">*</font> 输入新密码',
					name : 'newPassword',
					inputType : 'password',
					id : 'pass1',
					regex: /^[-a-zA-Z0-9'=\\[\];,.?"`~!@#$%^&*()_+|{}:"<>/]{8,20}$/,
					regexText:'只能输入8到20位字母‘a-z’或‘A-Z’、数字‘0-9’、特殊字符！',
					allowBlank : false
				},{
					xtype : 'textfield',
					fieldLabel: '<font color="red">*</font> 再次输入新密码',
					inputType : 'password',
					name : 'newPassword2',
					id:'pass2',
					vtype:"password",//自定义的验证类型
	                vtypeText:"两次密码不一致！",
	                confirmTo:"pass1",//要比较的另外一个的组件的id
					allowBlank : false
				}],
				buttonAlign:'center',
				fbar: ['单击确定即可修改密码，请牢记新密码！',{
					text: '确定',
					action: 'confirm',
					handler:'onConfirm'
				}]
			}],

			listeners : {
				scope : this,
				deactivate : function(panel){
					panel.down('field[name="password"]').reset();
					panel.down('field[name="newPassword"]').reset();
					panel.down('field[name="newPassword2"]').reset();
				}
			}
		});

		this.callParent(arguments);
	},

	onReset: function() {
		this.up('form').getForm().reset();
	}
});