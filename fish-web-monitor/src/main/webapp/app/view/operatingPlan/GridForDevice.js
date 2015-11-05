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
				text : Eway.locale.button.search,
				iconCls : 'queryBtn',
				action : 'query'
			}, {
				text : Eway.locale.button.link,
				iconCls : 'connectBtn',
				action : 'link'
			} ],
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
				header : Eway.locale.report.openplan.name,
				width : 100,
				dataIndex : 'name'
			}, {
				header : Eway.locale.report.openplan.state,
				width : 100,
				dataIndex : 'planType',
				renderer : function(value, metadata, record) {
					if (value == "DATE") {
						return Eway.locale.report.openplan.date;
					} else if (value == "WEEK") {
						return Eway.locale.report.openplan.week;
					}
				}
			}, {
				header : Eway.locale.report.openplan.machineQuantity,
				width : 130,
				dataIndex : 'deviceCount',
			}, {
				header : Eway.locale.report.openplan.state,
				width : 130,
				dataIndex : 'planStateType',
			}, {
				header : Eway.locale.report.openplan.openDate,
				width : 120,
				dataIndex : 'startDate'
			}, {
				header : Eway.locale.report.openplan.closeDate,
				width : 120,
				dataIndex : 'endDate'
			}, {
				header : Eway.locale.version.View.remark,
				dataIndex : 'desc',
				flex : 1
			}, {
				header : Eway.locale.report.openplan.createDateTime,
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