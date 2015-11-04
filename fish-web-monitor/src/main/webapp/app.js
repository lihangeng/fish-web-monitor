Ext.application({
		name : 'Eway',
		appFolder : 'app',
		controllers : [
			'Main'
		],
		extend: 'Ext.app.Application',
		requires: ['Eway.view.Viewport','Eway.lib.Util'],
		autoCreateViewport: false,
		launch : function(){
			Ext.get('loading').destroy();
			Ext.setGlyphFontFamily('FontAwesome');
			Ext.create('Eway.view.Viewport');
			/**会话超时检测*/
			Ext.Ajax.on('requestcomplete',function(connection,response,options){
			try{
				   	var sessionStatus = response.getResponseHeader("sessionStatus");
				   	if(sessionStatus == "timeout"){
				   			Ext.util.Cookies.set("lastEwayPage","");
				  			Ext.Msg.show({
								title:'提示',
					            width: 350,
					            modal:true,
					            closable:true,
					            buttons:Ext.Msg.OK,
					            fn:function(btn,text){
						   			if (btn == 'ok'){
						   				window.location='login.jsp';
						   			}
						   		},
								msg : '会话超时，3秒后自动跳转到登录页面',
								icon : Ext.MessageBox.INFO
							});

							Ext.defer(function(){
								window.location='login.jsp';
							},3000);
				     }}catch(e){}
			}, this);

			//删除背景图片
//			var bd = Ext.dom.Query.select('body')[0];
//			bd.style.background = "";
		}
	});

