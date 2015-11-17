Ext.define('Eway.view.agent.remote.RemoteBrowseShowMergeFile', {
	extend: 'Ext.window.Window',
	alias: 'widget.remote_RemoteBrowseShowMergeFile',

	requires: ['Eway.view.agent.remote.MergeDownLoadFileListGrid'],

	title: Eway.locale.agent.remote.distanceExplorer,
	modal: true,
	defaults: {
	        border: false,
	        width:700,
	        height :500
	},
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				xtype: 'remote_mergeDownLoadFileGrid'
			}]
		});

		this.callParent(arguments);
	}

});