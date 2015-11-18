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
				text: EwayLocale.button.apply,
				iconCls : 'connectBtn',
				glyph : 0xf0c1,
				code : 'planLink',
				action : 'link'
			}, {
				text: EwayLocale.button.info,
				glyph : 0xf129,
				iconCls : 'detailBtn',
				action : 'queryDetail'
			}, {
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : EwayLocale.button.add,
				glyph : 0xf067,
				code : 'planAdd',
				action : 'add'
			}, {
				text: EwayLocale.button.update,
				glyph : 0xf040,
				code : 'planUpdate',
				action : 'update'
			}, {
				text: EwayLocale.button.remove,
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
					if(planStateType == EwayLocale.commen.comboxStatus.close||planStateType == EwayLocale.commen.comboxStatus.pastDue){
						return 'user-online';
					}else if(planStateType == EwayLocale.commen.comboxStatus.pastDueSoon){
						return 'user-yellow';
					}
				}
			},
			columns : [ {
				header : EwayLocale.report.plan.name,
				width : 180,
				dataIndex : 'name'
			}, {
				header : EwayLocale.report.plan.type,
				width : 130,
				dataIndex : 'planType',
				renderer : function(value, metadata, record) {
					if (value == "DATE") {
						return EwayLocale.report.serviceplan.date;
					} else if (value == "WEEK") {
						return EwayLocale.report.serviceplan.week;
					}
				}
			}, {
				header : EwayLocale.report.serviceplan.machineQuantity,
				width : 130,
				dataIndex : 'deviceCount',
			}, {
				header : EwayLocale.report.serviceplan.state,
				width : 130,
				dataIndex : 'planStateType'
			}, {
				header : EwayLocale.report.serviceplan.openDate,
				width : 180,
				dataIndex : 'startDate'
			}, {
				header : EwayLocale.report.serviceplan.closeDate,
				width : 180,
				dataIndex : 'endDate'
			},/* {
				header : EwayLocale.version.View.remark,
				dataIndex : 'desc',
				flex : 1
			},*/ {
				header : EwayLocale.report.serviceplan.createDateTime,
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