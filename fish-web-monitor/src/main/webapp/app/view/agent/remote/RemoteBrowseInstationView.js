Ext.define('Eway.view.agent.remote.RemoteBrowseInstationView',{
	extend: 'Ext.window.Window',
	alias: 'widget.remote_remoteBrowseInstationView',
	
	requires: ['Eway.view.agent.remote.RemoteBrowseInstationGrid'], 
	
//	id: 'deviceInstationInfoWinId',
	title: EwayLocale.agent.remote.softwayList,
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