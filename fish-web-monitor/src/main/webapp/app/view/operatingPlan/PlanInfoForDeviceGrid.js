
Ext.define('Eway.view.operatingPlan.PlanInfoForDeviceGrid', {
	alias: 'widget.planInfo_device_grid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	
	border : false,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.operatingPlan.OpenPlanForDevice');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar : [ '->',  {
				text : '详情',
				iconCls : 'detailBtn',
				action : 'deviceQueryDetail'
			}, {
				text: '关联',
				iconCls :'connectBtn',
				action: 'deviceLink'
			}, {
				text: '解除',
				iconCls :'deleteBtn',
				action: 'deviceUnlink'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ {
				header : '名称',
				width : 100,
				dataIndex : 'name'
			}, {
				header : '类型',
				width : 80,
				dataIndex : 'planType',
				renderer : function(value, metadata, record) {
					if (value == "DATE") {
						return "日期";
					} else if (value == "WEEK") {
						return "星期";
					}
				}
			}, {
				header : '状态',
				width : 80,
				dataIndex : 'planStateType',
			}, {
				header : '有效开始时间',
				width : 100,
				dataIndex : 'startDate'
			}, {
				header : '有效结束时间',
				width : 100,
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
			}]
		});

		this.callParent(arguments);
	}
});