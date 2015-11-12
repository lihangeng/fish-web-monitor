Ext.define('Eway.view.personal.UpdatePwdController', {
	extend: 'Ext.app.ViewController',
	alias:'controller.updatePwd',

	init: function() {
	},

    /**
     * 修改密码确定：
     */
	onConfirm: function(){
		var me = this;
		var form = this.getView().down('form');
		var username = ewayUser.getCode();
		var password = form.down('field[name="password"]').getValue();
		var newPassword = form.down('field[name="newPassword"]').getValue();
		if(form.getForm().isValid()){
			if(password==newPassword){
				Eway.alert(Eway.locale.personal.pwdSameNoChange);
				form.getForm().reset();
				form.down('field[name="code"]').setValue(ewayUser.getCode());
				form.down('field[name="name"]').setValue(ewayUser.getName());
			}else{
				Ext.Ajax.request({
					method : 'POST',
					url : 'api/login/updatePassword',
					params :{
						username: username,
						password : password,
						newPassword : newPassword
					},
					success : function(response){
						var object = Ext.decode(response.responseText);
						if(object.success == true){
							Eway.alert(object.message);
							Ext.Function.defer(
							function(){
								me.onExit();
							},3000		
							);
								
						}else{
							Eway.alert(object.errorMsg);
						}
					},
					failure: function(){
						Eway.alert(Eway.locale.personal.reOperate);
					},
					scope:this
				});
			}
		}
	},

	onExit : function(){
		Ext.Cometd.disconnect();
		Ext.Ajax.request({
			method : 'POST',
			url : 'api/logout',
			success : function(response){
				window.location='login.jsp';
			},
			failure : function(response){
				window.location='login.jsp';
			}
		});
	}
});