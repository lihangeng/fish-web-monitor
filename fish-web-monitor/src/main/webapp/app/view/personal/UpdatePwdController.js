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
				Eway.alert('输入的新密码与旧密码相同,不可修改.');
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
							Ext.MessageBox.alert('提示',object.message,function callback(){
								me.onExit();
							});
						}else{
							Eway.alert(object.errorMsg);
						}
					},
					failure: function(){
						Eway.alert("无法修改密码,请重新操作.");
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
				window.location='login.html';
			},
			failure : function(response){
				window.location='login.html';
			}
		});
	}
});