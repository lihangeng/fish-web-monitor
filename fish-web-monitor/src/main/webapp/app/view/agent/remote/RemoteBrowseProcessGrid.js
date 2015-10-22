Ext.define('Eway.view.agent.remote.RemoteBrowseProcessGrid', {
	extend: 'Eway.view.base.Grid',
	alias: 'widget.remote_remoteBrowseProcessGrid',
	
//	store: 'agent.RemoteBrowseProcess',
	
//	id: 'processGridId',
	border: false,
	autoFit: true,
	columnLines: true, 
//	viewConfig: {
//		loadMask:false //为了解决me.loadMask.bindStore(store)错误的问题
//	},
	requires : ['Eway.store.agent.RemoteBrowseProcess'],
	initComponent: function(){
		var store = Ext.create('Eway.store.agent.RemoteBrowseProcess',{});
		Ext.apply(this, {
			initRegion : true,
			store : store,
			columns : [{
				header: Eway.locale.agent.remote.number,
				renderer: function(value, cellmeta, record, rowIndex){
					return rowIndex+1;
				},
				flex: 1
			},
			{
				header: Eway.locale.agent.remote.impressionName,
				sortable : true,
				dataIndex: 'name',
				flex: 1
			},{
				header: Eway.locale.agent.remote.userName,
				sortable: true,
				dataIndex: 'user',
				flex: 1
			}/*,{
				header: 'cpu',
				sortable: true,
				dataIndex: 'cpuRate',
				flex: 1
			}*/,{
				header: Eway.locale.agent.remote.memoryRate,
				sortable: true,
				dataIndex: 'memoryRate',
				flex: 1
			},{
				header: Eway.locale.commen.description,
				sortable: true,
				dataIndex: 'description',
				flex: 4
			}]
//		,
//			dockedItems: [{  //分页栏
//		        xtype: 'pagingtoolbar',
//		        store: this.store,   // same store GridPanel is using
//		        dock: 'bottom',
//		        displayInfo: true
//   			 }]
		});
		
		this.callParent(arguments);
	}
	
	
});