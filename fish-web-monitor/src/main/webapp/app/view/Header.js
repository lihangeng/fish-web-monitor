Ext.define('Eway.view.Header', {
	alias: 'widget.appheader',
    extend: 'Ext.toolbar.Toolbar',
    uses : ['Ext.ux.TransparentButton'],
    id : 'appheader',
    defaults :{
    	xtype: 'transparentbutton'
    },
    style:{
    	backgroundColor: '#1c396f'//'#3892d3'
    },

    items: [
        {
            xtype : 'label',
            text : EwayLocale.ATMV,
            style : 'font-size : 18px; color : white;'
        },"->",{
        	xtype:'textfield',
        	name:'terminalId',
        	enableKeyEvents :true,
        	emptyText :'设备号'
        },{
        	tooltip:EwayLocale.button.search,
        	glyph : 0xf002,
        	action:'signleQuery'
        },{
        	xtype:'tbtext',
        	text: EwayLocale.welcome+Eway.user.getName(),
        	style : 'font-size : 14px; color : white;'
        },{
        	tooltip:EwayLocale.personalConf,
        	glyph : 0xf013,
        	action:'personalSettings'
        },{
            glyph : 0xf059,
            tooltip:EwayLocale.systemHelp,
        	action:'systemHelp'
        },{
        	tooltip : EwayLocale.exitSystem,
            glyph : 0xf011,
            handler : function(){
            	Ext.MessageBox.confirm(EwayLocale.exitSystemComfirmTitle, EwayLocale.exitSystemComfirmInfo,function(button,text){
        			if(button == 'yes'){
        			Ext.Cometd.disconnect();//关闭订阅连接
    				Ext.Ajax.request({
    					method : 'POST',
    					url : 'api/logout',
    					success : function(response){
    						Ext.util.Cookies.set("lastEwayPage","");
    						window.location='login.jsp';
    					},
    					failure : function(response){
    						window.location='login.jsp';
    					}
    				});}
        		});
			}
         }],
         listeners: {
     		beforerender:function( _this, eOpts ){
     			var store = _this.up("viewport").getOtherStore();
     			var isShow = false;
     			Ext.Array.forEach(store,function(item,index,store){
     				if(item.get("code")=='deviceView'){
     					isShow = true;
     				}
     			});
		    	if(isShow){
		    		_this.items.items[2].show();
		    		_this.items.items[3].show();
		    	}else{
		    		_this.items.items[2].hide();
		    		_this.items.items[3].hide();
		    	}
     		}
     	}
});
