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
	//actionColumns判断当前列是否有权限操作
	onColumnBeforeRender: function(column){
		var has = false;
		
		Ext.each(Ext.fishButtons,function(code){
			if(column.code == code){
				has = true;
				return;
			}
		});
		column.removeAll();
		column.setVisible(has);
		column.setHidden(!has);
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