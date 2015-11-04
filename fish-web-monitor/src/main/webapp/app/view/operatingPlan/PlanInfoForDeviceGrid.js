
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
				text : Eway.locale.tip.business.device.detail,
				iconCls : 'detailBtn',
				action : 'deviceQueryDetail'
			}, {
				text:Eway.locale.button.link,
				iconCls :'connectBtn',
				action: 'deviceLink'
			}, {
				text:Eway.locale.button.unlink,
				iconCls :'deleteBtn',
				action: 'deviceUnlink'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ {
				header : Eway.locale.report.plan.name,
				width : 100,
				dataIndex : 'name'
			}, {
				header : Eway.locale.report.plan.type,
				width : 80,
				dataIndex : 'planType',
				renderer : function(value, metadata, record) {
					if (value == "DATE") {
						return Eway.locale.report.openplan.date;
					} else if (value == "WEEK") {
						return Eway.locale.report.openplan.week;
					}
				}
			}, {
				header : Eway.locale.report.openplan.state,
				width : 80,
				dataIndex : 'planStateType',
			}, {
				header : Eway.locale.report.openplan.openDate,
				width : 100,
				dataIndex : 'startDate'
			}, {
				header : Eway.locale.report.openplan.closeDate,
				width : 100,
				dataIndex : 'endDate'
			}, {
				header : Eway.locale.version.View.remark,
				dataIndex : 'desc',
				flex : 1
			}, {
				header :Eway.locale.report.openplan.createDateTime,
				dataIndex : 'createDateTime',
				width : 160,
				fiex : 1
			}]
		});

		this.callParent(arguments);
	}
});