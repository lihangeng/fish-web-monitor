
Ext.define('Eway.view.agent.remote.RemoteBrowseDiskGrid', {
	alias: 'widget.remote_remoteBrowseDiskGrid',
	extend: 'Eway.view.base.Grid',

	requires: [
		'Eway.lib.Util'
	],

	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.agent.RemoteBrowseDisk',{});
		Ext.apply(this, {
			initRegion : true,
			viewConfig : {
				loadMask   : {
       			 	msg : Eway.locale.agent.remote.loadData
        		}
			},
			tbar: [{
				text: Eway.locale.agent.remote.refresh,
				iconCls:'refresh-btn-custom',
				handler: this.onReload,
				scope: this
			}],
			store : store,
			columns : [{
				header : Eway.locale.agent.remote.name,
				width : 150,
				sortable : true,
				renderer: this.iconBackground,
				dataIndex : 'name'
			}, {
				header : Eway.locale.agent.remote.format,
				dataIndex : 'fileSys',
				sortable : true
			}, {
				header : Eway.locale.agent.remote.totalSize,
				dataIndex : 'totalSize',
				renderer: function(value,metadata,record){
	               return (value/(1024*1024)).toFixed(1)+"GB";
				},
				sortable : true
			},{
				header : Eway.locale.agent.remote.freeSize,
				dataIndex : 'freeSize',
				renderer: function(value,metadata,record){
	               return (value/(1024*1024)).toFixed(1)+"GB";
				},
				flex: 1,
				sortable : true
			}]
		});

		this.callParent(arguments);
	},

	iconBackground: function(value,metadata,record){
		    return '<span class="disk-name-blackground">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'+value;
	},

	onReload: function() {
		var ip = this.up("remote_remoteBrowseView").down('textfield[name="ip"]').getValue();
		this.getStore().load({
			params : {
				ip : ip
			},
			scope   : this,
		    callback: function(records, operation, success) {
		        if(success==true){
				}else{
					Eway.alert(Eway.locale.agent.remote.refreshFailure);
				}
			}
		});
	}

});