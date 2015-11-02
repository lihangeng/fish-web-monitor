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
				text: Eway.locale.button.apply,
				iconCls : 'connectBtn',
				code : 'planLink',
				action : 'link'
			}, {
				text: Eway.locale.button.info,
				iconCls : 'detailBtn',
				action : 'queryDetail'
			}, {
				text: Eway.locale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : Eway.locale.button.add,
				glyph : 0xf067,
				code : 'planAdd',
				action : 'add'
			}, {
				text: Eway.locale.button.update,
				glyph : 0xf040,
				code : 'planUpdate',
				action : 'update'
			}, {
				text: Eway.locale.button.remove,
				glyph : 0xf014,
				code : 'planDel',
				action : 'remove'
			}/*,{
				text : '导出',
				iconCls : 'exportToExcel',
				code : 'planExport',
				action : 'exportPlan'
				}*/],
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
				header : Eway.locale.report.plan.name,
				width : 180,
				dataIndex : 'name'
			}, {
				header : Eway.locale.report.plan.type,
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