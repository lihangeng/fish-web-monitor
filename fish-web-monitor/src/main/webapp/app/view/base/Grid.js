Ext.define('Eway.view.base.Grid', {
	extend: 'Ext.grid.Panel',

	requires: ['Ext.toolbar.Paging'],

	//自动加载store
	autoLoadStore : undefined,
	//是否分页
	autoPage : undefined,
	//
	loadMask : true,
	//加载本地机构的数据
	initRegion : undefined,
	//是否触发单击表格行的事件
	fireClickRowEvent : true,
	//是否选中表格的第一行
	selectfirst : true,
//	pageSize : Eway.PAGE_SIZE,
	pageSize : 10,

	viewConfig : {
		forceFit : true,
		stripeRows : true,
		enableTextSelection : true
	},

	initComponent: function() {
		if(this.fireClickRowEvent){
			this.on('rowclick', function(grid, rowIndex){
				var record = grid.getStore().getAt(rowIndex);
				this.fireEvent('select', grid, record);
			}, this);
		}
		
		this.on('viewready',function(grid){
			if(this.selectfirst){
				grid.getSelectionModel().select(0);
			}
		},this);

		this.callParent(arguments);
	},

	reload : function() {
		this.getStore().reload();
	}

});