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
				header : EwayLocale.refs.terminalId,//'设备编号',
				dataIndex: 'code',
				width:200
			}, {
				header : EwayLocale.refs.ip,//'IP地址',
				dataIndex : 'ip',
				width: 150
			},{
				header : EwayLocale.refs.orgName,
				dataIndex : 'orgName',
				width: 250
			}, {
				header: EwayLocale.refs.devType,
				dataIndex : 'deviceType',
				width: 180
			},{
				header: EwayLocale.version.View.nowVersionNo,//'当前版本',
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