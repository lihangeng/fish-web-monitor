Ext.define('Eway.view.agent.remote.screen.CameraView', {
	extend : 'Ext.window.Window',
	alias : 'widget.agent_remote_screen_cameraView',

	requires : [ 'Eway.view.agent.remote.screen.CameraGrid' ],
	title : Eway.locale.agent.remote.screen.screenCamera,
	modal : true,
	resizable : false,
	constrainHeader : true,
	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'agent_remote_screen_cameraGrid'
			}
		});
		this.callParent(arguments);
	}
});