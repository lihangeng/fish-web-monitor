Ext.define('Eway.view.operatingPlan.Grid', {
	alias : 'widget.operatingPlan_grid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	initComponent : function() {
		var store = Ext.create('Eway.store.operatingPlan.OpenPlan');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar : [ '->', {
				text : '应用',
				iconCls : 'connectBtn',
				code : 'planLink',
				action : 'link'
			}, {
				text : '详情',
				iconCls : 'detailBtn',
				action : 'queryDetail'
			}, {
				text : '查询',
				iconCls : 'queryBtn',
				action : 'query'
			}, {
				text : '增加',
				iconCls : 'addBtn',
				code : 'planAdd',
				action : 'add'
			}, {
				text : '更改',
				iconCls : 'updateBtn',
				code : 'planUpdate',
				action : 'update'
			}, {
				text : '删除',
				iconCls : 'deleteBtn',
				code : 'planDel',
				action : 'remove'
			},{
				text : '导出',
				iconCls : 'exportToExcel',
				code : 'planExport',
				action : 'exportPlan'
				}],
			viewConfig : {
				forceFit : true,
				stripeRows : true,
				getRowClass: function(record, index) {
					var planStateType = record.get('planStateType');
					if(planStateType == '停用'||planStateType == '过期'){
						return 'user-online';
					}else if(planStateType == '即将过期'){
						return 'user-yellow';
					}
				}
			},
			columns : [ {
				header : '名称',
				width : 180,
				dataIndex : 'name'
			}, {
				header : '类型',
				width : 130,
				dataIndex : 'planType',
				renderer : function(value, metadata, record) {
					if (value == "DATE") {
						return "日期";
					} else if (value == "WEEK") {
						return "星期";
					}
				}
			}, {
				header : '设备数量',
				width : 130,
				dataIndex : 'deviceCount',
			}, {
				header : '状态',
				width : 130,
				dataIndex : 'planStateType'
			}, {
				header : '有效开始时间',
				width : 180,
				dataIndex : 'startDate'
			}, {
				header : '有效结束时间',
				width : 180,
				dataIndex : 'endDate'
			}, {
				header : '备注',
				dataIndex : 'desc',
				flex : 1
			}, {
				header : '创建时间',
				dataIndex : 'createDateTime',
				width : 180,
				fiex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});