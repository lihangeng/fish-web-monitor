Ext.define('Eway.view.Header', {
	alias: 'widget.appheader',
    extend: 'Ext.toolbar.Toolbar',
    uses : ['Ext.ux.TransparentButton'],
    id : 'appheader',
    defaults :{
    	xtype: 'transparentbutton',
    },
    style:{
    	backgroundColor: '#1c396f'//'#3892d3'
    },
    items: [
        {
            xtype : 'label',
            text : Eway.locale.ATMV,
            style : 'font-size : 18px; color : white;'
        },"->",{
        	xtype:'tbtext',
        	text: Eway.locale.welcome+ewayUser.getName(),
        	style : 'font-size : 14px; color : white;'
        },{
        	tooltip:Eway.locale.personalConf,
        	glyph : 0xf013,
        	action:'personalSettings'
        },{
            glyph : 0xf059,
            tooltip:Eway.locale.systemHelp,
        	action:'systemHelp'
        },{
        	tooltip : Eway.locale.exitSystem,
            glyph : 0xf011,
            handler : function(){
				Ext.Cometd.disconnect();//关闭订阅连接
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
         }]
});
