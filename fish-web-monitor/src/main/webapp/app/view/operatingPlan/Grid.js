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
				glyph : 0xf0c1,
				code : 'planLink',
				action : 'link'
			}, {
				text: Eway.locale.button.info,
				glyph : 0xf129,
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
					if(planStateType == Eway.locale.commen.comboxStatus.close||planStateType == Eway.locale.commen.comboxStatus.pastDue){
						return 'user-online';
					}else if(planStateType == Eway.locale.commen.comboxStatus.pastDueSoon){
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
						return Eway.locale.report.plan.date;
					} else if (value == "WEEK") {
						return Eway.locale.report.plan.week;
					}
				}
			}, {
				header : Eway.locale.report.openplan.machineQuantity,
				width : 130,
				dataIndex : 'deviceCount',
			}, {
				header : Eway.locale.report.openplan.state,
				width : 130,
				dataIndex : 'planStateType'
			}, {
				header : Eway.locale.report.openplan.openDate,
				width : 180,
				dataIndex : 'startDate'
			}, {
				header : Eway.locale.report.openplan.closeDate,
				width : 180,
				dataIndex : 'endDate'
			}, {
				header : Eway.locale.version.View.remark,
				dataIndex : 'desc',
				flex : 1
			}, {
				header : Eway.locale.report.openplan.createDateTime,
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