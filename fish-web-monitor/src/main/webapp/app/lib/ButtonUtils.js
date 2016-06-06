Ext.define('Eway.lib.ButtonUtils', {
	singleton: true,

	onButtonBeforeRender: function(button){
		var has = false;
		Ext.each(Ext.fishButtons,function(code){
			if(button.code == code){
				has = true;
				return;
			}
		});
		
		button.setVisible(has);
	},
	
	loadButtons : function(mainController){
		Ext.Ajax.request({
			method : 'GET',
			url : 'api/login/button',
			params : {
				userId : Eway.user.getId()
			},
			success : function(response){
				var text = Ext.decode(response.responseText);
				Ext.fishButtons = text.data;
				mainController.initTabPanel();
			}
		});
	}
	
});