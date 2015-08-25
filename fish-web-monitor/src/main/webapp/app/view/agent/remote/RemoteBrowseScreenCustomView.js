Ext.define('Eway.view.agent.remote.RemoteBrowseScreenCustomView',{
	extend: 'Ext.window.Window',
	alias: 'widget.agent_remote_remoteBrowseScreenCustomView',
	
	requires: ['Eway.view.agent.remote.RemoteBrowseScreenCustomGrid'], 
	
	title: '远程抓屏',
	modal: true,
	resizable: false,
	constrainHeader: true,
	maximizable: true,
	
	width : 500,
	height : 400,
	layout : 'fit',
	initComponent: function(){
		Ext.apply(this, {
			items : {
				xtype: 'remote_remoteBrowseScreenCustomGrid'
			}
		});
		this.callParent(arguments);
	}
});