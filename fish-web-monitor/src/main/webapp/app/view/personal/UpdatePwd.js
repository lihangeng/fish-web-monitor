
Ext.define('Eway.view.personal.UpdatePwd', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.updatePwd',

	requires: ['Eway.view.field.person.Code','Eway.view.field.person.UserName','Eway.view.personal.UpdatePwdController'],

	title: Eway.locale.personal.changePwd,
	
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
					fieldLabel : Eway.locale.personal.nowLogin,
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
				    fieldLabel: '<font color="red">*</font>'+Eway.locale.personal.inputOldPwd,
				    name : 'password',
				    inputType : 'password',
				    allowBlank : false
				},{
					xtype : 'textfield',
					fieldLabel: '<font color="red">*</font>'+Eway.locale.personal.inputNewPwd,
					name : 'newPassword',
					inputType : 'password',
					id : 'pass1',
					regex: /^[-a-zA-Z0-9'=\\[\];,.?"`~!@#$%^&*()_+|{}:"<>/]{8,20}$/,
					regexText:Eway.locale.personal.inputVali,
					allowBlank : false
				},{
					xtype : 'textfield',
					fieldLabel: '<font color="red">*</font>'+Eway.locale.personal.inputAgain,
					inputType : 'password',
					name : 'newPassword2',
					id:'pass2',
					vtype:"password",//自定义的验证类型
	                vtypeText:Eway.locale.personal.pwdNotSame,
	                confirmTo:"pass1",//要比较的另外一个的组件的id
					allowBlank : false
				}],
				buttonAlign:'center',
				fbar: [Eway.locale.personal.rememberPwd,{
					text: Eway.locale.button.sure,
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