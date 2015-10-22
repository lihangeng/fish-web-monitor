
Ext.define('Eway.view.agent.remote.RemoteBrowseView', {
	extend: 'Ext.window.Window',
	alias: 'widget.remote_remoteBrowseView',

	requires: ['Eway.view.agent.remote.RemoteBrowseDiskGrid',
	           'Eway.view.agent.remote.RemoteBrowseFileSystemGrid'],

	title: Eway.locale.agent.remote.distanceExplorer,
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	layout: 'card',
	activeItem: 0,
	/*bodyStyle: 'padding:15px',*/
	defaults: {
	        border: false
	},

	initComponent: function() {
		Ext.apply(this, {
			items : [{
				xtype: 'remote_remoteBrowseDiskGrid'
			},{
				xtype: 'remote_remoteBrowseFileSystemGrid'
			}]
		});

		this.callParent(arguments);
	}

});