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
			
			var store = Ext.create('Ext.data.TreeStore', {
			    proxy: {
			        type: 'ajax',
			        url : 'api/login/mymenu/'+Eway.user.getId(),
			        reader: {
				        type: 'json',
				        rootProperty: 'data'
				    }
			    },
			    fields : [ 'id','code','text','leaf','iconCls','treeNode'],
			    defaultRootId :'0',
			    autoLoad: true,
			    listeners:{
			    	load:function(_this, records, successful, operation, node, eOpts ){
			    		var array = new Array();
			    		Ext.Array.forEach(records,function(item,index,allItems){
			    			if(!item.get("treeNode")){
			    				store.remove(item);
			    				array.push(item);
			    			}
			    		});
						Ext.create('Eway.view.Viewport',{treepanelStore:store,otherStore:array});
			    	}
			    }
			});
			
			/**会话超时检测*/
			Ext.Ajax.on('requestcomplete',function(connection,response,options){
			try{
				   	var sessionStatus = response.getResponseHeader("sessionStatus");
				   	if(sessionStatus == "timeout"){
				   			Ext.util.Cookies.set("lastEwayPage","");
				  			Ext.Msg.show({
								title:EwayLocale.confirm.title,
					            width: 350,
					            modal:true,
					            closable:true,
					            buttons:Ext.Msg.OK,
					            fn:function(btn,text){
						   			if (btn == 'ok'){
						   				window.location='login.jsp';
						   			}
						   		},
								msg : EwayLocale.confirm.timeout,
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

