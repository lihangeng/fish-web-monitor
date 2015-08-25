Ext.define('Eway.view.agent.remote.RemoteBrowseInstationView',{
	extend: 'Ext.window.Window',
	alias: 'widget.remote_remoteBrowseInstationView',
	
	requires: ['Eway.view.agent.remote.RemoteBrowseInstationGrid'], 
	
//	id: 'deviceInstationInfoWinId',
	title: '软件安装列表',
	modal: true,
	resizable: false,
	constrainHeader: true,
	maximizable: true,
	layout : 'fit',
	
	initComponent: function(){
		Ext.apply(this, {
			items : {
				xtype: 'remote_remoteBrowseInstationGrid'
			}
		});
		this.callParent(arguments);
	}
});