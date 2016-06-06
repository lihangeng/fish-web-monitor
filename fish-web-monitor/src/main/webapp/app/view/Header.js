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
				});
			}
         }]
});
