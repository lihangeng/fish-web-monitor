Ext.define('Eway.view.agent.remote.RemoteBrowseProcessView',{
	extend: 'Ext.window.Window',
	alias: 'widget.remote_remoteBrowseProcessView',
	
	requires: [
		'Eway.view.agent.remote.RemoteBrowseProcessGrid'
	], 
	
	title: '系统进程信息',
	modal: true,
	resizable: false,
	constrainHeader: true,
	maximizable: true,
	height : 500,
	width : 700,
	layout : 'fit',
	initComponent: function(){
		Ext.apply(this, {
			items : {
				xtype: 'remote_remoteBrowseProcessGrid'
			}/*,
			listeners : {
				activate : function(win){
				}
			}*/
		});
		this.callParent(arguments);
	}
});