Ext.define('Eway.view.version.download.LinkedDeviceGrid', {
	alias : 'widget.version_download_linkedDeviceGrid',
	extend : 'Ext.grid.Panel',

	requires : [ 'Eway.lib.Util','Eway.model.version.LinkedDevice'],

	border : false,
	viewConfig : {
		forceFit : true,
		stripeRows : true
	},

	initComponent : function() {
		var gridStore = Ext.create('Eway.store.version.LinkedDevice',{});
		Ext.apply(this, {
			initRegion : true,
			store: gridStore,
			columns : [{
	            xtype: 'rownumberer',
	            width: 50,
	            sortable: false
        	},
			{
				header : '设备编号',
				dataIndex: 'code',
				width:100
			}, {
				header : 'IP地址',
				dataIndex : 'ip',
				width: 110
			},{
				header : Eway.locale.refs.orgName,
				dataIndex : 'orgName'
			}, {
				header: Eway.locale.refs.devType,
				dataIndex : 'deviceType'
			},{
				header: '当前版本',
				dataIndex : 'deviceVersion',
				flex : true
			}/*,{
				header:'移除',
				xtype:'actioncolumn',
				items : [{
					icon : 'resources/images/remove.png',
					tooltip: '从下发列表中移除',
					handler : function(grid,rowIndex,colIndex){
						var store = grid.getStore();
						var record = store.getAt(rowIndex);
						var selectableGrid =  this.up('window').down('version_download_selectableDeviceGrid');
						selectableGrid.getSelectionModel().deselect(record.get('id'));
					},
					scope : this
				}]
			} */]
		});

		this.callParent(arguments);
	}
});