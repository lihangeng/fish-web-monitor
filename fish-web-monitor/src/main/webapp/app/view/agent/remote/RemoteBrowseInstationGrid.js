Ext.define('Eway.view.agent.remote.RemoteBrowseInstationGrid', {
	extend: 'Eway.view.base.Grid',
	alias: 'widget.remote_remoteBrowseInstationGrid',
	
	store: 'agent.RemoteBrowseInstation',
	
//	id: 'remoteBrowseInstationGridId',
	border: false,
	autoFit: true,
	columnLines: true, 
//	viewConfig: {
//		loadMask:false //为了解决me.loadMask.bindStore(store)错误的问题
//	},
	
	initComponent: function(){
		Ext.apply(this, {
			initRegion : true,
			columns : [
			           {
						header: EwayLocale.agent.remote.number,
						renderer: function(value, cellmeta, record, rowIndex){
						return rowIndex+1;
						},
						flex: 1
			},
			{
				header: EwayLocale.agent.remote.programName,
				sortable : true,
				dataIndex: 'programName',
				flex: 2
			},{
				header: EwayLocale.agent.remote.version,
				sortable: true,
				dataIndex: 'version',
				flex: 1
			},{
				header: EwayLocale.agent.remote.publisher,
				sortable: true,
				dataIndex: 'publisher',
				flex: 2
			},{
				header: EwayLocale.commen.installDate,
				sortable: true,
				dataIndex: 'installDate',
				flex: 1
			},{
				header: EwayLocale.agent.remote.diskUsed,
				sortable: true,
				dataIndex: 'diskUsed',
				flex: 1
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