Ext.define('Eway.view.operatingPlan.GridForDevice', {
	alias : 'widget.operatingPlan_gridForDevice',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,

	initComponent : function() {
		var store = Ext.create('Eway.store.operatingPlan.OpenPlan');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar : [ '->', {
				text : '查询',
				iconCls : 'queryBtn',
				action : 'query'
			}, {
				text : '关联',
				iconCls : 'connectBtn',
				action : 'link'
			} ],
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
				width : 100,
				dataIndex : 'name'
			}, {
				header : '类型',
				width : 100,
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
				dataIndex : 'planStateType',
			}, {
				header : '有效开始时间',
				width : 120,
				dataIndex : 'startDate'
			}, {
				header : '有效结束时间',
				width : 120,
				dataIndex : 'endDate'
			}, {
				header : '备注',
				dataIndex : 'desc',
				flex : 1
			}, {
				header : '创建时间',
				dataIndex : 'createDateTime',
				width : 160,
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