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
            text : '自助设备监控系统(ATMV)',
            style : 'font-size : 18px; color : white;'
        },"->",{
        	xtype:'tbtext',
        	text: "欢迎你,"+ewayUser.getName(),
        	style : 'font-size : 14px; color : white;'
        },{
        	tooltip:'个人设置',
        	glyph : 0xf013,
        	action:'personalSettings'
        },{
            glyph : 0xf059,
            tooltip:'系统帮助',
        	action:'systemHelp'
        },{
        	tooltip : '退出系统',
            glyph : 0xf011,
            handler : function(){
				Ext.Cometd.disconnect();//关闭订阅连接
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
         }]
});
