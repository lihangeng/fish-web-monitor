
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
       			 	msg : '加载数据中，请稍候...'
        		}
			},
			tbar: [{
				text: '刷新',
				iconCls:'refresh-btn-custom',
				handler: this.onReload,
				scope: this
			}],
			store : store,
			columns : [{
				header : '名称',
				width : 150,
				sortable : true,
				renderer: this.iconBackground,
				dataIndex : 'name'
			}, {
				header : '格式',
				dataIndex : 'fileSys',
				sortable : true
			}, {
				header : '总大小',
				dataIndex : 'totalSize',
				renderer: function(value,metadata,record){
	               return (value/(1024*1024)).toFixed(1)+"GB";
				},
				sortable : true
			},{
				header : '可用空间',
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
					Eway.alert("刷新失败！");
				}
			}
		});
	}

});